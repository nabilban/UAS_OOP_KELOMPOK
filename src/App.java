import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enums.Genres;
import failures.Failures;
import models.Makanan;
import models.Minuman;
import models.Movie;
import services.MovieManagement;
import utils.ScreenCleaning;

public class App {
        public static void main(String[] args) throws Exception {
                var scanner = new Scanner(System.in);

                var movieManagement = new MovieManagement();
                // pre-defined Data
                movieManagement.movies.add(
                                new Movie(
                                                "Star Wars: Episode III - Revenge of the Sith",
                                                "George Lucas",
                                                LocalDate.of(2005, 05, 12),
                                                true,
                                                new ArrayList<Genres>(List.of(
                                                                Genres.Action,
                                                                Genres.Adventure,
                                                                Genres.Fantasy,
                                                                Genres.SciFi)),
                                                8));
                movieManagement.movies.add(
                                new Movie(
                                                "Frozen",
                                                "Chris Buck, Jennifer Lee",
                                                LocalDate.of(2013, 11, 29),
                                                false,
                                                new ArrayList<Genres>(List.of(
                                                                Genres.Animation,
                                                                Genres.Adventure,
                                                                Genres.Family)),
                                                8));
                movieManagement.items.add(
                                new Makanan("PopCorn", 30000, 10));
                movieManagement.items.add(
                                new Makanan("Nachos", 25000, 15));
                movieManagement.items.add(
                                new Makanan("Hot Dog", 20000, 0));
                movieManagement.items.add(
                                new Minuman("Soda", 15000, 0));
                movieManagement.items.add(
                                new Minuman("Coffee", 20000, 25));
                movieManagement.items.add(
                                new Minuman("Tea", 15000, 20));
                movieManagement.items.add(
                                new Minuman("Juice", 25000, 0));

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
                                        movieManagement.registerAsViewer();
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
                                        Failures.showInvalidOptionMessage();
                                        continue;
                        }
                }
                scanner.close();
        }
}
