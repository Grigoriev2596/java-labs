package bsu.edummf.task12.entity;

import bsu.edummf.task12.exception.AuctionException;
import bsu.edummf.task12.util.AuctionStatus;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Auctioneer extends Thread{
    private String firstName;
    private String lastName;
    private int balance;
    private List<Item> items = new LinkedList<>();
    private int dismissalCount = 0;
    private Auction auction;

    public Auctioneer(String firstName, String lastName, int balance) throws AuctionException {
        setBalance(balance);
        setFirstName(firstName);
        setLastName(lastName);
    }


    @Override
    public void run() {
        while(auction.getAuctionStatus() != AuctionStatus.AUCTION_ENDED) {
            Random random = new Random();
            try {
                Thread.sleep((long) (random.nextDouble()*10000));


                if (auction.getAuctionStatus() == AuctionStatus.THE_AUCTION_IS_IN_PROGRESS && (auction.getPriceOfCurrentItem() + 500 < balance)) {
                    if (random.nextDouble() > 0.9 || auction.getPotentialBuyer() == this || dismissalCount != 0) continue;
                    auction.increasePrice(this, random.nextInt(500));
                } else if (auction.getAuctionStatus() == AuctionStatus.WAITING_FOR_PAYMENT
                && auction.getPotentialBuyer().equals(this)) {
                    if (balance > auction.getPriceOfCurrentItem()) {
                        auction.makeDeposit(auction.getPriceOfCurrentItem());
                        balance -= auction.getPriceOfCurrentItem();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void decreaseDismissal() throws AuctionException {
        if (dismissalCount <= 0) {
            throw new AuctionException("Dismissal value can't be less than zero");
        }
        dismissalCount--;

    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public void setDismissalCount(int dismissalCount) {
        this.dismissalCount = dismissalCount;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBalance(int balance) throws AuctionException {
        if (balance < 0) {
            throw new AuctionException("Balance can't be negative.");
        }
        this.balance = balance;

    }

    public void addItem(Item item) {
        try{
            items.add((Item) item.clone());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getDismissalCount() {
        return dismissalCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBalance() {
        return balance;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Auctioneer{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", items=").append(items);
        sb.append(", dismissalCount=").append(dismissalCount);
        sb.append('}');
        return sb.toString();
    }
}
