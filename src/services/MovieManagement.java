package services;

import java.util.ArrayList;
import java.util.Scanner;

import abstracts.Items;
import models.*;
import utils.ScreenCleaning;

public class MovieManagement {
    Scanner scanner = new Scanner(System.in);
    public ArrayList<Movie> movies = new ArrayList<Movie>();
    public ArrayList<Items> Items = new ArrayList<Items>();

    public void registerAsViewer() {
        ScreenCleaning.ClearScreen();
        System.out.println("== Masuk Sebagai Penonton == ");

        var isRunning = true;
        while (isRunning) {
            System.out.println("----------------------------------------");
            System.out.println("Berikut merupakan opsi yang tersedia");
            System.out.println("(1). Daftar film");
            System.out.println("(2). Daftar film yang sedang tayang");
            System.out.println("(3). Tampilkan Makanan dan Minuman");
            System.out.println("(4). kembali ke menu awal");
            System.out.println("----------------------------------------");

            System.out.print("- Input: ");
            var inputOption = scanner.nextInt();

            switch (inputOption) {
                case 1:
                    showAllMovies();
                    break;
                case 2:
                    showPlayingMovies();
                    break;
                case 3:
                    showAllItems();
                    break;
                case 4:
                    ScreenCleaning.ClearScreen();
                    isRunning = false;
                    break;
                default:
                    ScreenCleaning.ClearScreen();
                    System.out.println("-------------------------------------");
                    System.out.println("| Opsi tidak valid                  |");
                    System.out.println("| Mohon masukkan opsi yang tersedia |");
                    System.out.println("-------------------------------------");
                    continue;
            }
        }
    }

    public void showAllMovies() {
        ScreenCleaning.ClearScreen();

        if (this.movies.size() == 0) {
            System.out.println("--------------------------------");
            System.out.println("| Tidak ada film yang tersedia |");
            System.out.println("---------------------------------");
        }

        for (var movie : movies) {
            System.out.println("---------------------------------------------");
            System.out.println("- Title        : " + movie.getTitle());
            System.out.println("- Director     : " + movie.getDirector());
            System.out.println("- On air       : " + movie.getOnAir());
            System.out.println("- Genres       : " + movie.getGenres());
            System.out.println("- Rating       : " + movie.getRating());
            System.out.println("---------------------------------------------");
        }
    }

    public void showPlayingMovies() {
        ScreenCleaning.ClearScreen();

        if (this.movies.size() == 0) {
            System.out.println("--------------------------------------");
            System.out.println("| Tidak ada film yang sedang diputar |");
            System.out.println("--------------------------------------");
        }

        for (var movie : movies) {
            if (movie.getOnAir() == true) {
                System.out.println("---------------------------------------------");
                System.out.println("- Title        : " + movie.getTitle());
                System.out.println("- Director     : " + movie.getDirector());
                System.out.println("- On air       : " + movie.getOnAir());
                System.out.println("- Genres       : " + movie.getGenres());
                System.out.println("- Rating       : " + movie.getRating());
                System.out.println("---------------------------------------------");
            }
        }
    }

    public void showAllItems() {
        ScreenCleaning.ClearScreen();

        if (this.Items.size() == 0) {
            System.out.println("--------------------------------");
            System.out.println("| Tidak ada item yang tersedia |");
            System.out.println("---------------------------------");
        }
        ArrayList<Items> outOfStockItems = new ArrayList<Items>();

        System.out.println("== Daftar Makanan ==");
        System.out.println("---------------------------------------------");
        for (var item : Items) {
            if (item instanceof models.Makanan) {
                models.Makanan makanan = (models.Makanan) item;
                if (makanan.getStok() > 0) {
                    makanan.displayDetails();
                    System.out.println("---------------------------------------------");
                } else {
                    outOfStockItems.add(makanan);
                }
            }
        }

        System.out.println("== Daftar Minuman ==");
        System.out.println("---------------------------------------------");
        for (var item : Items) {
            if (item instanceof models.Minuman) {
                models.Minuman minuman = (models.Minuman) item;
                if (minuman.getStok() > 0) {
                    minuman.displayDetails();
                    System.out.println("---------------------------------------------");
                } else {
                    outOfStockItems.add(minuman);
                }
            }
        }
        if (!outOfStockItems.isEmpty()) {
            System.out.println("== Daftar Menu yang Habis ==");
            System.out.println("---------------------------------------------");
            for (var item : outOfStockItems) {
                System.out.println(item.getName() + " Habis");
            }
            System.out.println("---------------------------------------------");
        }
    }
}
