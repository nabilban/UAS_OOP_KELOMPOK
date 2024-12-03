package services;

import java.util.ArrayList;
import java.util.Scanner;

import failures.Failures;
import models.*;
import abstracts.Items;
import utils.ScreenCleaning;

public class MovieManagement {
    Scanner scanner = new Scanner(System.in);
    public ArrayList<Movie> movies = new ArrayList<>();
    public ArrayList<Items> items = new ArrayList<>();

    public void registerAsViewer() {
        ScreenCleaning.ClearScreen();
        System.out.println("== Masuk Sebagai Penonton ==");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("----------------------------------------");
            System.out.println("Berikut merupakan opsi yang tersedia:");
            System.out.println("(1) Daftar film");
            System.out.println("(2) Daftar film yang sedang tayang");
            System.out.println("(3) Tampilkan Makanan dan Minuman");
            System.out.println("(4) Pesan Makanan dan Minuman");
            System.out.println("(5) Kembali ke menu awal");
            System.out.println("----------------------------------------");

            System.out.print("- Input: ");
            int inputOption = getIntInput();

            switch (inputOption) {
                case 1 -> showAllMovies();
                case 2 -> showPlayingMovies();
                case 3 -> showAllItems();
                case 4 -> orderItems();
                case 5 -> {
                    ScreenCleaning.ClearScreen();
                    isRunning = false;
                }
                default -> Failures.showInvalidOptionMessage();
            }
        }
    }

    private void showAllMovies() {
        ScreenCleaning.ClearScreen();

        if (movies.isEmpty()) {
            Failures.showNoFilmAvailableMessage();
            return;
        }

        for (Movie movie : movies) {
            System.out.println("---------------------------------------------");
            System.out.println("- Title   : " + movie.getTitle());
            System.out.println("- Director: " + movie.getDirector());
            System.out.println("- On air  : " + movie.getOnAir());
            System.out.println("- Genres  : " + movie.getGenres());
            System.out.println("- Rating  : " + movie.getRating());
            System.out.println("---------------------------------------------");
        }
    }

    private void showPlayingMovies() {
        ScreenCleaning.ClearScreen();

        if (movies.isEmpty()) {
            Failures.showNoFilmPlayedMessage();
            ;
            return;
        }

        for (Movie movie : movies) {
            if (movie.getOnAir()) {
                System.out.println("---------------------------------------------");
                System.out.println("- Title   : " + movie.getTitle());
                System.out.println("- Director: " + movie.getDirector());
                System.out.println("- Genres  : " + movie.getGenres());
                System.out.println("- Rating  : " + movie.getRating());
                System.out.println("---------------------------------------------");
            }
        }
    }

    private void showAllItems() {
        ScreenCleaning.ClearScreen();

        if (items.isEmpty()) {

            return;
        }

        ArrayList<Items> outOfStockItems = new ArrayList<>();

        System.out.println("== Daftar Makanan ==");
        System.out.println("---------------------------------------------");
        for (Items item : items) {
            if (item instanceof models.Makanan makanan && makanan.getStok() > 0) {
                makanan.displayDetails();
            } else if (item instanceof models.Makanan makanan) {
                outOfStockItems.add(makanan);
            }
        }

        System.out.println("== Daftar Minuman ==");
        System.out.println("---------------------------------------------");
        for (Items item : items) {
            if (item instanceof models.Minuman minuman && minuman.getStok() > 0) {
                minuman.displayDetails();
            } else if (item instanceof models.Minuman minuman) {
                outOfStockItems.add(minuman);
            }
        }

        if (!outOfStockItems.isEmpty()) {
            System.out.println("== Daftar Menu yang Habis ==");
            System.out.println("---------------------------------------------");
            for (Items item : outOfStockItems) {
                System.out.println(item.getName() + " (Habis)");
            }
        }
    }

    private void orderItems() {
        showAllItems();
        if (items.isEmpty()) {
            Failures.showItemNotFoundMessage();
            return;
        }

        System.out.print("Masukkan ID item yang ingin dipesan: ");
        int itemID = getIntInput();
        if (itemID == -1) {
            Failures.showItemInvalidInputMessage();
            return;
        }

        Items selectedItem = findItemById(itemID);
        if (selectedItem == null) {
            Failures.showItemNotFoundMessage();
            return;
        }

        System.out.print("Masukkan jumlah item yang ingin dipesan: ");
        int quantity = getIntInput();
        if (quantity <= 0) {
            Failures.showItemInvalidInputMessage();
            return;
        }

        if (!processOrder(selectedItem, quantity)) {
            Failures.showItemOutOfStockMessage();
        } else {
            System.out.println("Pesanan berhasil! Sisa stok: " + getRemainingStock(selectedItem));
        }
    }

    private boolean processOrder(Items item, int quantity) {
        if (item instanceof models.Makanan makanan) {
            if (makanan.getStok() >= quantity) {
                makanan.setStok(makanan.getStok() - quantity);
                return true;
            }
        } else if (item instanceof models.Minuman minuman) {
            if (minuman.getStok() >= quantity) {
                minuman.setStok(minuman.getStok() - quantity);
                return true;
            }
        }
        return false;
    }

    private int getRemainingStock(Items item) {
        if (item instanceof models.Makanan makanan) {
            return makanan.getStok();
        } else if (item instanceof models.Minuman minuman) {
            return minuman.getStok();
        }
        return 0;
    }

    private int getIntInput() {
        if (!scanner.hasNextInt()) {
            scanner.next(); // Clear invalid input
            return -1;
        }
        return scanner.nextInt();
    }

    private Items findItemById(int itemID) {
        for (Items item : items) {
            if (item.getId() == itemID) {
                return item;
            }
        }
        return null;
    }
}
