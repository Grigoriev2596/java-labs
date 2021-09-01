package bsu.edummf.task12.entity;

import bsu.edummf.task12.exception.AuctionException;
import bsu.edummf.task12.util.AuctionStatus;
import bsu.edummf.task12.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Auction extends Thread {
    private List<Item> items;
    private List<Auctioneer> auctioneers;
    private int balance = 0;
    private int indexOfCurrentItem = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Auctioneer potentialBuyer;
    private int passedTime = 0;
    private boolean paymentConfirmed = false;
    private AuctionStatus auctionStatus = AuctionStatus.NOT_STARTED_YET;

    public Auction(List<Item> items, List<Auctioneer> auctioneers) {
        this.items = new ArrayList<Item>(items);
        this.auctioneers = auctioneers;
    }

    public void makeDeposit(int deposit) {
        lock.lock();
        if (deposit >= items.get(indexOfCurrentItem).getPrice()) {
            paymentConfirmed = true;
        }
        try {
            balance += deposit;
        } finally {
            lock.unlock();
        }
    }

    public void increasePrice(Auctioneer potentialBuyer, int value) {
        lock.lock();
        try {
            items.get(indexOfCurrentItem).setPrice(items.get(indexOfCurrentItem).getPrice() + value);
            this.potentialBuyer = potentialBuyer;
            passedTime = 0;
            System.out.print(this.potentialBuyer.getFirstName() + " "+ this.potentialBuyer.getLastName());
            System.out.println(" increased the price to " + items.get(indexOfCurrentItem).getPrice() + "$");
        } finally {
            lock.unlock();
        }

    }

    private void waitingForIncreasingPrice() {
        passedTime = 0;
        try{
            auctionStatus = AuctionStatus.THE_AUCTION_IS_IN_PROGRESS;
            while(passedTime < Duration.SECONDS_FOR_EACH_INCREASING) {
                Thread.sleep(Duration.MILLISECONDS_AUCTION_RATE);
                passedTime++;
            }
            auctionStatus = AuctionStatus.NOT_DEFINED;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void waitingForPayment() {
        passedTime = 0;
        try{
            auctionStatus = AuctionStatus.WAITING_FOR_PAYMENT;
            while(passedTime < Duration.SECONDS_FOR_EACH_INCREASING) {
                Thread.sleep(Duration.MILLISECONDS_AUCTION_RATE);
                if (paymentConfirmed) {
                    paymentConfirmed = false;
                    System.out.println("payment was successful\n");
                    potentialBuyer.addItem(items.get(indexOfCurrentItem));
                    return;
                }
                passedTime++;
            }
            auctionStatus = AuctionStatus.NOT_DEFINED;
            potentialBuyer.setDismissalCount(3);
            System.out.println("Buyer did not pay for the item. The case is awaiting further investigation.\n");
            System.out.println("Auctioneer suspended from auction for 3 times.\n");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void decreasingDismissalCount() {
        try{
            for(int i = 0; i < auctioneers.size(); i++) {
                if (auctioneers.get(i).getDismissalCount() > 0) {
                    auctioneers.get(i).decreaseDismissal();
                }
            }
        } catch (AuctionException e) {
            System.out.println(e.getMessage());
        }
    }

    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }

    public Auctioneer getPotentialBuyer() {
        return potentialBuyer;
    }

    public int getPriceOfCurrentItem() {
        return items.get(indexOfCurrentItem).getPrice();
    }

    @Override
    public void run() {
        System.out.println("Auction is started!\n");
        while(indexOfCurrentItem < items.size()) {
            System.out.println("Current item:");
            System.out.println(items.get(indexOfCurrentItem) + "\n");

            waitingForIncreasingPrice();
            if (potentialBuyer == null) {
                System.out.println("The item remains at stock. Nobody bought the item.\n");
            } else {
                System.out.print("\n" + this.potentialBuyer.getFirstName() + " "+ this.potentialBuyer.getLastName());
                System.out.println(" won this item");
                System.out.println("waiting for payment...");
                waitingForPayment();
            }
            indexOfCurrentItem++;
            potentialBuyer = null;
            decreasingDismissalCount();
        }
        auctionStatus = AuctionStatus.AUCTION_ENDED;
        System.out.println("Auction is over");
        System.out.println("Results:");
        for(Auctioneer auctioneer: auctioneers) {
            System.out.println(auctioneer);
        }
    }
}
