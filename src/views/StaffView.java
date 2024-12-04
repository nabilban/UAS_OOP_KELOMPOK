package views;

import utils.ScreenCleaning;

public class StaffView {
    public static void displayStaffHeader() {
        ScreenCleaning.ClearScreen();
        System.out.println("-------------------------------");
        System.out.println("| == Masuk sebagai Pegawai == |");
        System.out.println("-------------------------------");
    }

    public static void displayStaffMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Berikut merupakan opsi yang tersedia:");
        System.out.println("(1) Daftar film");
        System.out.println("(2) Daftar film yang sedang tayang");
        System.out.println("(3) Tambah film");
        System.out.println("(4) Daftar makanan dan minuman");
        System.out.println("(5) Tambah makanan dan minuman");
        System.out.println("(6) Kembali ke menu awal");
        System.out.println("----------------------------------------");
    }

}
