package views;

import java.util.ArrayList;

import models.Items;

import utils.ScreenCleaning;

public class ItemsView {

    public static void displayItemsList(ArrayList<Items> items) {
        ScreenCleaning.ClearScreen();

        if (items.isEmpty()) {
            Failures.showItemNotAvailableMessage();
            return;
        }

        ArrayList<Items> outOfStockItems = new ArrayList<>();
        System.out.println("------------------------");
        System.out.println("| == Daftar Makanan == |");
        System.out.println("------------------------");
        for (Items item : items) {
            if (item instanceof models.Makanan makanan && makanan.getStok() > 0) {
                System.out.println("---------------------------------------------");
                makanan.displayDetails();
            } else if (item instanceof models.Makanan makanan) {
                outOfStockItems.add(makanan);
            }
        }

        System.out.println("------------------------");
        System.out.println("| == Daftar Minuman == |");
        System.out.println("------------------------");
        for (Items item : items) {
            if (item instanceof models.Minuman minuman && minuman.getStok() > 0) {
                System.out.println("---------------------------------------------");
                minuman.displayDetails();
            } else if (item instanceof models.Minuman minuman) {
                outOfStockItems.add(minuman);
            }
        }

        if (!outOfStockItems.isEmpty()) {
            System.out.println("--------------------------------");
            System.out.println("| == Daftar Menu yang Habis == |");
            System.out.println("--------------------------------");
            for (Items item : outOfStockItems) {
                System.out.println("- " + item.getName() + " (Habis)");
            }
        }
    }
}
