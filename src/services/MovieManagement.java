package services;

import java.util.ArrayList;
import java.util.Scanner;

import models.Audiance;
import models.Movie;
import utils.ScreenCleaning;

public class MovieManagement {
    Scanner scanner = new Scanner(System.in);
    public ArrayList<Movie> movies = new ArrayList<Movie>();
    public ArrayList<Audiance> audiances = new ArrayList<Audiance>();

    public void registerAsViewer() {
        ScreenCleaning.ClearScreen();
        System.out.println("== Masuk Sebagai Penonton == ");

        var isRunning = true;
        while (isRunning) {
            System.out.println("----------------------------------------");
            System.out.println("Berikut merupakan opsi yang tersedia");
            System.out.println("(1). Daftar film");
            System.out.println("(2). Datar film yang sedang tayang");
            System.out.println("(3). Beli tiket");
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
                    BuyTicket();
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
        System.out.println("== Daftar Film ==");

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
        System.out.println("== Daftar Film yang Sedang Tayang ==");

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

    public void BuyTicket() {
        ScreenCleaning.ClearScreen();
        System.out.println("== Beli Tiket == ");

        var isRunning = true;
        while (isRunning) {
            System.out.println("----------------------------------------");
            System.out.println("Berikut merupakan opsi yang tersedia");
            System.out.println("(1). Beli tiket");
            System.out.println("(2). Kembali ke menu sebelumnya");
            System.out.println("----------------------------------------");

            System.out.print("- Input: ");
            int inputOption = scanner.nextInt();

            switch (inputOption) {
                case 1:
                    ScreenCleaning.ClearScreen();

                    System.out.println("== Buat Tiket ==");
                    System.out.println("----------------------------------------");
                    System.out.print("| - Siapa nama Anda: ");
                    String audiancesName = scanner.next();

                    System.out.print("| - Berapa umur Anda: ");
                    int audiancesAge = scanner.nextInt();

                    System.out.print("| - Apa gender Anda? (Pria atau Wanita): ");
                    String audiancesGender = scanner.next();

                    var newAudiance = new Audiance(
                        audiancesName, 
                        audiancesAge, 
                        audiancesGender,
                        true
                    );
                    
                    this.audiances.add(newAudiance);

                    ScreenCleaning.ClearScreen();
                    System.out.println("-------------------------");
                    System.out.println("| Anda telah beli tiket |");
                    System.out.println("| Terima kasih          |");
                    System.out.println("-------------------------");

                    System.out.println("--------------------------");
                    System.out.println("-- Informasi Tiket Anda --");
                    System.out.println("--------------------------");
                    System.out.println("| Name   : " + newAudiance.getName());
                    System.out.println("| Age    : " + newAudiance.getAge());
                    System.out.println("| Gender : " + newAudiance.getGender());
                    System.out.println("--------------------------");
                    break;
                case 2:
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
}
