import java.util.Scanner;

import utils.ScreenCleaning;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);

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
                    System.out.println("Masuk sebagai penonton");
                    break;
                case 2:
                    ScreenCleaning.ClearScreen();
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
                    ScreenCleaning.ClearScreen();
                    System.out.println("-------------------------------------");
                    System.out.println("| Opsi tidak valid                  |");
                    System.out.println("| Mohon masukkan opsi yang tersedia |");
                    System.out.println("-------------------------------------");
                    continue;
            }
        }
        scanner.close();
    }
}
