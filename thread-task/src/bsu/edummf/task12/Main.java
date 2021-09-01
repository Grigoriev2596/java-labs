package bsu.edummf.task12;

import bsu.edummf.task12.entity.Auction;
import bsu.edummf.task12.entity.Auctioneer;
import bsu.edummf.task12.entity.Item;
import bsu.edummf.task12.exception.AuctionException;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            Auctioneer auctioneer1 = new Auctioneer("Egor", "Grigoriev", 2500);
            Auctioneer auctioneer2 = new Auctioneer("Petya", "Ivanov", 1500);
            Auctioneer auctioneer3 = new Auctioneer("Vasya", "Lepehin", 5200);
            Auctioneer auctioneer4 = new Auctioneer("Maksim", "Bogdanov", 3300);

            List<Auctioneer> auctioneers = new ArrayList<Auctioneer>();
            auctioneers.add(auctioneer1);
            auctioneers.add(auctioneer2);
            auctioneers.add(auctioneer3);
            auctioneers.add(auctioneer4);

            Item item1 = new Item("Sofa", 250, 241252);
            Item item2 = new Item("The Origin of Species", 100, 123192);
            Item item3 = new Item("Mona Lisa", 300, 95204);
            Item item4 = new Item("Antique guns", 100, 22242);

            List<Item> items = new ArrayList<Item>();
            items.add(item1);
            items.add(item2);
            items.add(item3);
            items.add(item4);

            Auction auction = new Auction(items, auctioneers);

            auction.start();
            for(Auctioneer auctioneer : auctioneers) {
                auctioneer.setAuction(auction);
                auctioneer.start();
            }
        } catch (AuctionException e) {
            System.out.println(e.getMessage());
        }

    }
}
