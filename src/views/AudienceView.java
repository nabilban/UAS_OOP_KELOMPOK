package views;

import utils.ScreenCleaning;

public class AudienceView {
    public static void displayAudienceHeader() {
        ScreenCleaning.ClearScreen();
        System.out.println("--------------------------------");
        System.out.println("| == Masuk sebagai Penonton == |");
        System.out.println("--------------------------------");
    }

    public static void displayAudienceMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Berikut merupakan opsi yang tersedia:");
        System.out.println("(1) Daftar film");
        System.out.println("(2) Daftar film yang sedang tayang");
        System.out.println("(3) Pesan Tiket");
        System.out.println("(4) Tampilkan Makanan dan Minuman");
        System.out.println("(5) Pesan Makanan dan Minuman");
        System.out.println("(6) Kembali ke menu awal");
        System.out.println("----------------------------------------");

    }

}
