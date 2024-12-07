package views;

import java.util.ArrayList;

import models.ItemsModels;

import utils.ScreenCleaning;

public class ItemsView {

    public static void displayItemsList(ArrayList<ItemsModels> items) {
        ScreenCleaning.ClearScreen();

        if (items.isEmpty()) {
            FailuresView.showItemNotAvailableMessage();
            return;
        }

        ArrayList<ItemsModels> outOfStockItems = new ArrayList<>();
        System.out.println("------------------------");
        System.out.println("| == Daftar Makanan == |");
        System.out.println("------------------------");
        for (ItemsModels item : items) {
            if (item instanceof models.FoodsModel makanan && makanan.getStok() > 0) {
                System.out.println("---------------------------------------------");
                makanan.displayDetails();
            } else if (item instanceof models.FoodsModel makanan) {
                outOfStockItems.add(makanan);
            }
        }

        System.out.println("------------------------");
        System.out.println("| == Daftar Minuman == |");
        System.out.println("------------------------");
        for (ItemsModels item : items) {
            if (item instanceof models.DrinksModel minuman && minuman.getStok() > 0) {
                System.out.println("---------------------------------------------");
                minuman.displayDetails();
            } else if (item instanceof models.DrinksModel minuman) {
                outOfStockItems.add(minuman);
            }
        }

        if (!outOfStockItems.isEmpty()) {
            System.out.println("--------------------------------");
            System.out.println("| == Daftar Menu yang Habis == |");
            System.out.println("--------------------------------");
            for (ItemsModels item : outOfStockItems) {
                System.out.println("- " + item.getName() + " (Habis)");
            }
        }
    }
}
