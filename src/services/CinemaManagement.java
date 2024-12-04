package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import failures.Failures;
import initialize.Initialize;
import models.*;
import abstracts.Items;
import utils.ScreenCleaning;

public class CinemaManagement {
    Scanner scanner = new Scanner(System.in);
    public ArrayList<Movie> movies = new ArrayList<>();
    public ArrayList<Items> items = new ArrayList<>();

    public void registerAsViewer(Audiance audiance) {
        ScreenCleaning.ClearScreen();
        System.out.println("--------------------------------");
        System.out.println("| == Masuk sebagai Penonton == |");
        System.out.println("--------------------------------");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("----------------------------------------");
            System.out.println("Berikut merupakan opsi yang tersedia:");
            System.out.println("(1) Daftar film");
            System.out.println("(2) Daftar film yang sedang tayang");
            System.out.println("(3) Pesan Tiket");
            System.out.println("(4) Tampilkan Makanan dan Minuman");
            System.out.println("(5) Pesan Makanan dan Minuman");
            System.out.println("(6) Kembali ke menu awal");
            System.out.println("----------------------------------------");

            System.out.print("- Input: ");
            int inputOption = getIntInput();

            switch (inputOption) {
                case 1 -> showAllMovies();
                case 2 -> showPlayingMovies();
                case 3 -> orderTickets(audiance);
                case 4 -> showAllItems();
                case 5 -> orderItems(audiance);
                case 6 -> {
                    ScreenCleaning.ClearScreen();
                    isRunning = false;
                }
                default -> Failures.showInvalidOptionMessage();
            }
        }
    }

    public void registerAsStaff(Staff staff) {
        ScreenCleaning.ClearScreen();
        System.out.println("-------------------------------");
        System.out.println("| == Masuk sebagai Pegawai == |");
        System.out.println("-------------------------------");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("----------------------------------------");
            System.out.println("Berikut merupakan opsi yang tersedia:");
            System.out.println("(1) Lihat Daftar film");
            System.out.println("(2) Lihat Daftar film yang sedang tayang");
            System.out.println("(3) Tambah Daftar film");
            System.out.println("(4) Tampilkan Makanan dan Minuman");
            System.out.println("(5) Tambah Makanan dan Minuman");
            System.out.println("(6) Kembali ke menu awal");
            System.out.println("----------------------------------------");

            System.out.print("- Input: ");
            int inputOption = getIntInput();

            switch (inputOption) {
                case 1 -> showAllMovies();
                case 2 -> showPlayingMovies();
                case 3 -> addMovies();
                case 4 -> showAllItems();
                case 5 -> System.out.println("Tambah Stock Makanan dan Minuman");
                case 6 -> {
                    ScreenCleaning.ClearScreen();
                    isRunning = false;
                }
                default -> Failures.showInvalidOptionMessage();
            }
        }
    }

    private void orderTickets(Audiance audiance) {
        showPlayingMovies();
        if (movies.isEmpty()) {
            Failures.showNoFilmPlayedMessage();
            return;
        }

        System.out.print("Masukkan ID film yang ingin dipesan tiketnya: ");
        int movieID = getIntInput();
        Movie selectedMovie = findMovieById(movieID);

        if (selectedMovie == null) {
            Failures.showFilmInvalidInputMessage();
            return;
        }

        if (!selectedMovie.getOnAir()) {
            Failures.showFilmNotAvailableMessage();
            return;
        }

        if (selectedMovie.getSeatsAvailable() == 0) {
            Failures.showFilmOutOfSeatkMessage();
            return;
        }

        System.out.print("Masukkan jumlah tiket yang ingin dipesan: ");
        int ticketQuantity = getIntInput();
        if (ticketQuantity <= 0 || ticketQuantity > selectedMovie.getSeatsAvailable()) {
            Failures.showFilmInvalidInputMessage();
            return;
        }

        for (int i = 0; i < ticketQuantity; i++) {
            selectedMovie.addSeat(audiance);
        }
        // handle user ticket
        if (audiance.getHaveTicket() == false) {
            audiance.setHaveTicket(true);
        }
        // handle movie on air status
        if (selectedMovie.getSeatsAvailable() == 0) {
            selectedMovie.setOnAir(false);
        }

        ScreenCleaning.ClearScreen();
        System.out.println("Tiket berhasil dipesan untuk film: " + selectedMovie.getTitle());
        System.out.println("Jumlah tiket tersisa: " + selectedMovie.getSeatsAvailable());
    }

    private void showAllMovies() {
        ScreenCleaning.ClearScreen();
        System.out.println("---------------------");
        System.out.println("| == Daftar Film == |");
        System.out.println("---------------------");

        if (movies.isEmpty()) {
            Failures.showNoFilmAvailableMessage();
            return;
        }

        System.out.println("---------------------------------------------");
        for (Movie movie : movies) {
            System.out.println("- ID      : " + movie.getId());
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
            for (Movie movie : movies) {
                var onAirMovies = new ArrayList<>();
                if (movie.getOnAir()) {
                    onAirMovies.add(movie);
                }
                if (onAirMovies.isEmpty()) {
                    Failures.showNoFilmPlayedMessage();
                    return;
                }
            }
            Failures.showNoFilmAvailableMessage();
            return;
        }

        ScreenCleaning.ClearScreen();
        System.out.println("----------------------------------------");
        System.out.println("| == Daftar Film yang Sedang Tayang == |");
        System.out.println("----------------------------------------");

        for (Movie movie : movies) {
            if (movie.getOnAir()) {
                System.out.println("---------------------------------------------");
                System.out.println("- ID      : " + movie.getId());
                System.out.println("- Title   : " + movie.getTitle());
                System.out.println("- Director: " + movie.getDirector());
                System.out.println("- Genres  : " + movie.getGenres());
                System.out.println("- Rating  : " + movie.getRating());
                System.out.println(
                        "- seats   : " + movie.getSeatsAvailable() + " available(Maksimum " + movie.getMaxSeats()
                                + " seats)");
                System.out.println("---------------------------------------------");
            }
        }
    }

    private void showAllItems() {
        ScreenCleaning.ClearScreen();

        if (items.isEmpty()) {
            Failures.showItemNotAvailableMessage();
            return;
        }

        ArrayList<Items> outOfStockItems = new ArrayList<>();
        System.out.println("------------------------");
        System.out.println("| == Daftar Makanan == |");
        System.out.println("------------------------");
        for (Items item : items) {
            if (item instanceof models.Makanan makanan && makanan.getStok() > 0) {
                System.out.println("---------------------------------------------");
                makanan.displayDetails();
            } else if (item instanceof models.Makanan makanan) {
                outOfStockItems.add(makanan);
            }
        }

        System.out.println("------------------------");
        System.out.println("| == Daftar Minuman == |");
        System.out.println("------------------------");
        for (Items item : items) {
            if (item instanceof models.Minuman minuman && minuman.getStok() > 0) {
                System.out.println("---------------------------------------------");
                minuman.displayDetails();
            } else if (item instanceof models.Minuman minuman) {
                outOfStockItems.add(minuman);
            }
        }

        if (!outOfStockItems.isEmpty()) {
            System.out.println("--------------------------------");
            System.out.println("| == Daftar Menu yang Habis == |");
            System.out.println("--------------------------------");
            for (Items item : outOfStockItems) {
                System.out.println("- " +item.getName() + " (Habis)");
            }
        }
    }

    private void orderItems(Audiance audiance) {
        showAllItems();
        if (items.isEmpty()) {
            Failures.showItemNotAvailableMessage();
            return;
        }

        System.out.print("- Masukkan ID item yang ingin dipesan: ");
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

        System.out.print("- Masukkan jumlah item yang ingin dipesan: ");
        int quantity = getIntInput();
        if (quantity <= 0) {
            Failures.showItemInvalidInputMessage();
            return;
        }

        if (!processOrder(selectedItem, quantity)) {
            Failures.showItemOutOfStockMessage();
        } else {
            if (audiance.getItems() == null) {
                audiance.setItems(new ArrayList<>());
            }

            for (int i = 0; i < quantity; i++) {
                audiance.getItems().add(selectedItem);
            }

            ScreenCleaning.ClearScreen();
            System.out.println("Pesanan berhasil! Sisa stok: " + getRemainingStock(selectedItem));
            System.out.println("Jumlah item yang ditambahkan ke audiance: " + quantity);
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
            scanner.next();
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

    private Movie findMovieById(int movieID) {
        for (Movie movie : movies) {
            if (movie.getId() == movieID) {
                return movie;
            }
        }
        return null;
    }

    private void addMovies() {
        ScreenCleaning.ClearScreen();
        System.out.println("----------------------------");
        System.out.println("| == Tambah Daftar Film == |");
        System.out.println("----------------------------");

        System.out.println("----------------------------------------");

        System.out.print("Masukkan judul film: ");
        var moviesTitle = scanner.next();

        System.out.print("Masukkan nama director: ");
        var moviesDirector = scanner.next();

        System.out.print("Apakah film akan ditayangkan: ");
        var moviesIsOnAir = scanner.nextBoolean();

        System.out.print("Masukkan rating film: ");
        var moviesRating = scanner.nextInt();

        System.out.print("Masukkan tahun film dibuat: ");
        var moviesYear = scanner.nextInt();

        System.out.print("Masukkan bulan film dibuat: ");
        var moviesMonth = scanner.nextInt();

        System.out.print("Masukkan hari film dibuat (dalam angka): ");
        var moviesDate = scanner.nextInt();

        var movie = new Movie(
            moviesTitle, 
            moviesDirector, 
            LocalDate.of(moviesYear, moviesMonth, moviesDate), 
            moviesIsOnAir, 
            null, 
            moviesRating, 
            Initialize.getPredefinedAudience()
        );
        this.movies.add(movie);
    }
}
