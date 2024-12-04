package views;

import utils.ScreenCleaning;

public class Failures {

    public static void showInvalidInputMessage() {
        ScreenCleaning.ClearScreen();
        System.out.println("---------------------");
        System.out.println("| Input Tidak Valid |");
        System.out.println("---------------------");
    }

    public static void showFilmOutOfSeatkMessage() {
        ScreenCleaning.ClearScreen();
        System.out.println("---------------");
        System.out.println("| Kursi Penuh |");
        System.out.println("---------------");
    }

    public static void showFilmNotAvailableMessage() {
        ScreenCleaning.ClearScreen();
        System.out.println("--------------------------------");
        System.out.println("| Tidak ada film yang tersedia |");
        System.out.println("--------------------------------");
    }

    public static void showItemNotFoundMessage() {
        ScreenCleaning.ClearScreen();
        System.out.println("--------------------------------");
        System.out.println("| Item Tidak Dapet Ditemukan |");
        System.out.println("--------------------------------");
    }

    public static void showItemOutOfStockMessage() {
        ScreenCleaning.ClearScreen();
        System.out.println("------------------------");
        System.out.println("| Stok Tidak Mencukupi |");
        System.out.println("------------------------");
    }

    public static void showItemNotAvailableMessage() {
        ScreenCleaning.ClearScreen();
        System.out.println("--------------------------------");
        System.out.println("| Tidak ada item yang tersedia |");
        System.out.println("--------------------------------");
    }

    public static void showInvalidOptionMessage() {
        ScreenCleaning.ClearScreen();
        System.out.println("-------------------------------------");
        System.out.println("| Opsi tidak valid                  |");
        System.out.println("| Mohon masukkan opsi yang tersedia |");
        System.out.println("-------------------------------------");
    }

    public static void showNoFilmPlayedMessage() {
        ScreenCleaning.ClearScreen();
        System.out.println("--------------------------------------");
        System.out.println("| Tidak ada film yang sedang diputar |");
        System.out.println("--------------------------------------");
    }

    public static void showNoFilmAvailableMessage() {
        ScreenCleaning.ClearScreen();
        System.out.println("--------------------------------");
        System.out.println("| Tidak ada film yang tersedia |");
        System.out.println("--------------------------------");
    }
}