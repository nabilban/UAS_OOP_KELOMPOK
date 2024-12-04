import java.util.Scanner;

import models.Staff;
import utils.Initialize;
import utils.ScreenCleaning;
import views.Failures;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);
        // inisialisasi data
        var init = Initialize.initializeData();
        var movieManagement = init;

        // Main Menu
        ScreenCleaning.ClearScreen();
        System.out.println("---------------------------------------");
        System.out.println("| == Selamat Datang di Nova Cinema == |");
        System.out.println("---------------------------------------");

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
                    System.out.println("--------------------------------");
                    System.out.println("| == Masuk sebagai Penonton == |");
                    System.out.println("--------------------------------");

                    System.out.println("----------------------------------------");

                    System.out.print("Masukkan nama Anda: ");
                    var audiancesName = scanner.next();

                    System.out.print("Masukkan usia Anda: ");
                    var audiancesAge = scanner.nextInt();
                    if (audiancesAge < 0 || scanner.hasNextInt() == false) {
                        Failures.showInvalidInputMessage();
                        continue;
                    }

                    System.out.print("Masukkan gender Anda: ");
                    var audiancesGender = scanner.next();

                    var audience = new models.Audiance(
                            audiancesName,
                            audiancesAge,
                            audiancesGender,
                            false,
                            null);
                    movieManagement.registerAsViewer(audience);
                    break;
                case 2:
                    ScreenCleaning.ClearScreen();
                    System.out.println("-------------------------------");
                    System.out.println("| == Masuk sebagai Pegawai == |");
                    System.out.println("-------------------------------");

                    System.out.println("----------------------------------------");

                    System.out.print("Masukkan nama Anda: ");
                    var staffsName = scanner.next();

                    System.out.print("Masukkan usia Anda: ");
                    var staffsAge = scanner.nextInt();

                    System.out.print("Masukkan gender Anda: ");
                    var staffsGender = scanner.next();

                    var staff = new Staff(staffsName, staffsAge, staffsGender);
                    movieManagement.registerAsStaff(staff);
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
