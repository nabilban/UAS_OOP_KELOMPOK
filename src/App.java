import java.util.Scanner;

import initialize.Initialize;
import failures.Failures;

import utils.ScreenCleaning;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);
        // inisialisasi data
        var init = Initialize.initializeData();
        var movieManagement = init;

        // Main Menu
        ScreenCleaning.ClearScreen();
        System.out.println("== Selamat Datang di Nova Cinema ==");

        var isRunning = true;
        while (isRunning) {
            System.out.println("----------------------------------------");
            System.out.println("Berikut merupakan opsi yang tersedia");
            System.out.println("(1). Masuk sebagai penonton");
            System.out.println("(2). Masuk sebagai pegawai");
            System.out.println("(3). Keluar dari program");
            System.out.println("----------------------------------------");

            System.out.print("- Input: ");
            var inputOption = scanner.nextInt();

            switch (inputOption) {
                case 1:
                    ScreenCleaning.ClearScreen();
                    System.out.println("== Masuk Sebagai Penonton ==");
                    System.out.println("----------------------------------------");
                    System.out.print("Masukkan nama Anda: ");
                    var name = scanner.next();
                    System.out.print("Masukkan usia Anda: ");
                    var age = scanner.nextInt();
                    System.out.println("gender Anda: ");
                    var gender = scanner.next();
                    var audience = new models.Audiance(name, age, gender, false, null);
                    movieManagement.registerAsViewer(audience);
                    break;
                case 2:
                    ScreenCleaning.ClearScreen();
                    // TODO : Masuk sebagai pegawai , Masih belum diimplementasikan
                    // pegawai bisa tambah stock makanan dan minuman
                    // pefawai bisa tambah film
                    // Pegawai bisa melihat log penjualan makanan dan minuman
                    // Pegawai bisa melihat log penjualan tiket
                    System.out.println("Masuk sebagai pegawai");
                    break;
                case 3:
                    ScreenCleaning.ClearScreen();
                    System.out.println("-----------------------------");
                    System.out.println("| Telah keluar dari program |");
                    System.out.println("-----------------------------");
                    isRunning = false;
                    break;
                default:
                    Failures.showInvalidOptionMessage();
                    continue;
            }
        }
        scanner.close();
    }
}
