package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import models.*;
import utils.ScreenCleaning;
import views.Failures;
import views.Initialize;
import views.*;

public class CinemaManagement {
    Scanner scanner = new Scanner(System.in);
    public ArrayList<Movie> movies = new ArrayList<>();
    public ArrayList<Items> items = new ArrayList<>();

    public void registerAsViewer(Audiance audiance) {
        AudienceView.displayAudienceHeader();
        boolean isRunning = true;
        while (isRunning) {
            AudienceView.displayAudienceMenu();
            System.out.print("- Input: ");
            int inputOption = getIntInput();

            switch (inputOption) {
                case 1 -> MovieView.displayMovieList(movies);
                case 2 -> MovieView.displayPlayingMovies(movies);
                case 3 -> orderTickets(audiance);
                case 4 -> ItemsView.displayItemsList(items);
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
        StaffView.displayStaffHeader();
        boolean isRunning = true;
        while (isRunning) {
            StaffView.displayStaffMenu();
            System.out.print("- Input: ");
            int inputOption = getIntInput();

            switch (inputOption) {
                case 1 -> MovieView.displayMovieList(movies);
                case 2 -> MovieView.displayPlayingMovies(movies);
                case 3 -> addMovies();
                case 4 -> ItemsView.displayItemsList(items);
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
        MovieView.displayPlayingMovies(movies);
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

    private void orderItems(Audiance audiance) {
        ItemsView.displayItemsList(items);
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

        System.out.print("Apakah film akan ditayangkan (cth: true / false): ");
        var moviesIsOnAir = scanner.nextBoolean();

        System.out.print("Masukkan rating film: ");
        var moviesRating = scanner.nextInt();

        var moviesGenres = addGenres();

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
                moviesGenres,
                moviesRating,
                Initialize.getPredefinedAudience());
        this.movies.add(movie);
    }

    private ArrayList<String> addGenres() {
        var genres = new ArrayList<String>();
        boolean isRunning = true;
        System.out.println("Masukkan genre film (ketik 'selesai' untuk selesai): ");
        while (isRunning) {
            System.out.print("Masukkan genre: ");
            var input = scanner.next();
            if (!input.equalsIgnoreCase("selesai")) {
                genres.add(input);
            } else {
                isRunning = false;
            }
        }
        return genres;
    }
}
