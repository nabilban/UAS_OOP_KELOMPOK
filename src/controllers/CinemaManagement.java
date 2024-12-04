package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import models.*;
import utils.Initialize;
import utils.ScreenCleaning;
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
                case 5 -> addItems();
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
            Failures.showInvalidInputMessage();
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
            Failures.showInvalidInputMessage();
            return;
        }

        for (int i = 0; i < ticketQuantity; i++) {
            selectedMovie.addSeat(audiance);
        }

        if (audiance.getHaveTicket() == false) {
            audiance.setHaveTicket(true);
        }

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
            Failures.showInvalidInputMessage();
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
            Failures.showInvalidInputMessage();
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
        if (!scanner.hasNextBoolean()) {
            Failures.showInvalidOptionMessage();
            return;
        }

        System.out.print("Masukkan rating film 1 - 10 (cth: 8): ");
        var moviesRating = scanner.nextInt();
        if (moviesRating < 0 || moviesRating > 10 || scanner.hasNextInt() == false) {
            Failures.showInvalidOptionMessage();
            return;
        }

        var moviesGenres = addGenres();

        System.out.print("Masukkan tahun film dibuat: ");
        var moviesYear = scanner.nextInt();
        if (moviesYear <= 0 || scanner.hasNextInt() == false) {
            Failures.showInvalidOptionMessage();
            return;
        }

        System.out.print("Masukkan nomor bulan film dibuat (cth: 12): ");
        var moviesMonth = scanner.nextInt();
        if (moviesMonth <= 0 || moviesMonth > 12 || scanner.hasNextInt() == false) {
            Failures.showInvalidOptionMessage();
            return;
        }

        System.out.print("Masukkan hari film dibuat (dalam angka 0 - 31): ");
        var moviesDate = scanner.nextInt();
        if (moviesDate <= 0 || moviesDate > 31 || scanner.hasNextInt() == false) {
            Failures.showInvalidOptionMessage();
            return;
        }

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

    private void addItems() {
        ScreenCleaning.ClearScreen();
        System.out.println("----------------------------");
        System.out.println("| == Tambah Daftar Item == |");
        System.out.println("----------------------------");

        System.out.println("----------------------------------------");

        System.out.print("Masukkan jenis item (makanan / minuman): ");
        var itemType = scanner.next();
        if (!itemType.equalsIgnoreCase("makanan") && !itemType.equalsIgnoreCase("minuman")) {
            Failures.showInvalidInputMessage();
            return;
        }

        System.out.print("Masukkan nama item: ");
        var itemName = scanner.next();

        System.out.print("Masukkan harga item (cth: 30000): ");
        var itemPrice = scanner.nextInt();
        if (itemPrice <= 0 || scanner.hasNextInt() == false) {
            Failures.showInvalidInputMessage();
            return;
        }

        System.out.print("Masukkan stok item (cth: 30): ");
        var itemStock = scanner.nextInt();
        if (itemStock < 0 || scanner.hasNextInt() == false) {
            Failures.showInvalidInputMessage();
            return;
        }

        var item = itemType.equalsIgnoreCase("makanan")
                ? new Makanan(itemName, itemPrice, itemStock)
                : new Minuman(itemName, itemPrice, itemStock);
        this.items.add(item);
    }
}
