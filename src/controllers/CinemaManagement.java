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
    public ArrayList<MovieModel> movies = new ArrayList<>();
    public ArrayList<ItemsModels> items = new ArrayList<>();
    CreditView credit = new CreditView();

    public void registerAsViewer(AudianceModels audiance) {
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
                default -> FailuresView.showInvalidOptionMessage();
            }
        }
    }

    public void registerAsStaff(StaffModel staff) {
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
                default -> FailuresView.showInvalidOptionMessage();
            }
        }
    }

    private void orderTickets(AudianceModels audiance) {
        MovieView.displayPlayingMovies(movies);
        if (movies.isEmpty()) {
            FailuresView.showNoFilmPlayedMessage();
            return;
        }

        System.out.print("- Masukkan ID film yang ingin dipesan tiketnya: ");
        int movieID = getIntInput();
        MovieModel selectedMovie = findMovieById(movieID);

        if (selectedMovie == null) {
            FailuresView.showInvalidInputMessage();
            return;
        }

        if (!selectedMovie.getOnAir()) {
            FailuresView.showFilmNotAvailableMessage();
            return;
        }

        if (selectedMovie.getSeatsAvailable() == 0) {
            FailuresView.showFilmOutOfSeatkMessage();
            return;
        }

        System.out.print("- Masukkan jumlah tiket yang ingin dipesan: ");
        int ticketQuantity = getIntInput();
        if (ticketQuantity <= 0 || ticketQuantity > selectedMovie.getSeatsAvailable()) {
            FailuresView.showInvalidInputMessage();
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
        System.out.println("---------------------------------------------------");
        System.out.println("| Tiket berhasil dipesan untuk film: " + selectedMovie.getTitle());
        System.out.println("| Jumlah tiket tersisa: " + selectedMovie.getSeatsAvailable());
        System.out.println("---------------------------------------------------");
    }

    private void orderItems(AudianceModels audiance) {
        ItemsView.displayItemsList(items);
        if (items.isEmpty()) {
            FailuresView.showItemNotAvailableMessage();
            return;
        }

        System.out.println("");
        System.out.print("- Masukkan ID item yang ingin dipesan: ");
        int itemID = getIntInput();
        if (itemID == -1) {
            FailuresView.showInvalidInputMessage();
            return;
        }

        ItemsModels selectedItem = findItemById(itemID);
        if (selectedItem == null) {
            FailuresView.showItemNotFoundMessage();
            return;
        }

        System.out.print("- Masukkan jumlah item yang ingin dipesan: ");
        int quantity = getIntInput();
        if (quantity <= 0) {
            FailuresView.showInvalidInputMessage();
            return;
        }

        if (!processOrder(selectedItem, quantity)) {
            FailuresView.showItemOutOfStockMessage();
        } else {
            if (audiance.getItems() == null) {
                audiance.setItems(new ArrayList<>());
            }

            for (int i = 0; i < quantity; i++) {
                audiance.getItems().add(selectedItem);
            }

            ScreenCleaning.ClearScreen();
            System.out.println("---------------------------------------------------");
            System.out.println("| Pesanan berhasil! Sisa stok: " + getRemainingStock(selectedItem));
            System.out.println("| Jumlah item yang ditambahkan ke audiance: " + quantity);
            System.out.println("---------------------------------------------------");
        }
    }

    private boolean processOrder(ItemsModels item, int quantity) {
        if (item instanceof models.FoodsModel makanan) {
            if (makanan.getStok() >= quantity) {
                makanan.setStok(makanan.getStok() - quantity);
                return true;
            }
        } else if (item instanceof models.DrinksModel minuman) {
            if (minuman.getStok() >= quantity) {
                minuman.setStok(minuman.getStok() - quantity);
                return true;
            }
        }
        return false;
    }

    private int getRemainingStock(ItemsModels item) {
        if (item instanceof models.FoodsModel makanan) {
            return makanan.getStok();
        } else if (item instanceof models.DrinksModel minuman) {
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

    private ItemsModels findItemById(int itemID) {
        for (ItemsModels item : items) {
            if (item.getId() == itemID) {
                return item;
            }
        }
        return null;
    }

    private MovieModel findMovieById(int movieID) {
        for (MovieModel movie : movies) {
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

        System.out.println("----------------------------------------------------");
        System.out.println("| Note: Mohon, gunakan '_' sebagai pengganti spasi |");
        System.out.println("----------------------------------------------------");

        System.out.print("- Masukkan judul film: ");
        var moviesTitle = scanner.next();

        System.out.print("- Masukkan nama director: ");
        var moviesDirector = scanner.next();

        System.out.print("- Apakah film akan ditayangkan (cth: true / false): ");
        var moviesIsOnAir = scanner.nextBoolean();
        if (moviesIsOnAir != true && moviesIsOnAir != false) {
            FailuresView.showInvalidOptionMessage();
            return;
        }

        System.out.print("- Masukkan rating film 1 - 10 (cth: 8): ");
        var moviesRating = scanner.nextInt();
        if (moviesRating < 0 || moviesRating > 10) {
            FailuresView.showInvalidOptionMessage();
            return;
        }

        var moviesGenres = addGenres();

        System.out.print("- Masukkan tahun film direlase: ");
        var moviesYear = scanner.nextInt();
        if (moviesYear <= 0) {
            FailuresView.showInvalidOptionMessage();
            return;
        }

        System.out.print("- Masukkan nomor bulan film direlease (cth: 12): ");
        var moviesMonth = scanner.nextInt();
        if (moviesMonth <= 0 || moviesMonth > 12) {
            FailuresView.showInvalidOptionMessage();
            return;
        }

        System.out.print("- Masukkan hari film direlease (dalam angka 0 - 31): ");
        var moviesDate = scanner.nextInt();
        if (moviesDate <= 0 || moviesDate > 31) {
            FailuresView.showInvalidOptionMessage();
            return;
        }

        var movie = new MovieModel(
                moviesTitle,
                moviesDirector,
                LocalDate.of(moviesYear, moviesMonth, moviesDate),
                moviesIsOnAir,
                moviesGenres,
                moviesRating,
                Initialize.getPredefinedAudience());
        this.movies.add(movie);
        ScreenCleaning.ClearScreen();
    }

    private ArrayList<String> addGenres() {
        var genres = new ArrayList<String>();
        boolean isRunning = true;
        System.out.println("- Masukkan genre film (ketik 'selesai' untuk selesai): ");
        while (isRunning) {
            System.out.print("- Masukkan genre: ");
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

        System.out.println("----------------------------------------------------");
        System.out.println("| Note: Mohon, gunakan '_' sebagai pengganti spasi |");
        System.out.println("----------------------------------------------------");

        System.out.print("- Masukkan jenis item (makanan / minuman): ");
        var itemType = scanner.next();
        if (!itemType.equalsIgnoreCase("makanan") && !itemType.equalsIgnoreCase("minuman")) {
            FailuresView.showInvalidInputMessage();
            return;
        }

        System.out.print("- Masukkan nama item: ");
        var itemName = scanner.next();

        System.out.print("- Masukkan harga item (cth: 30000): ");
        var itemPrice = scanner.nextInt();
        if (itemPrice <= 0) {
            FailuresView.showInvalidInputMessage();
            return;
        }

        System.out.print("- Masukkan stok item (cth: 30): ");
        var itemStock = scanner.nextInt();
        if (itemStock < 0) {
            FailuresView.showInvalidInputMessage();
            return;
        }

        var item = itemType.equalsIgnoreCase("makanan")
                ? new FoodsModel(itemName, itemPrice, itemStock)
                : new DrinksModel(itemName, itemPrice, itemStock);
        this.items.add(item);
    }

    public void viewCredit() {
        ScreenCleaning.ClearScreen();
        System.out.println("----------------");
        System.out.println("| == Credit == |");
        System.out.println("----------------");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("----------------------------------------");
            System.out.println("Berikut merupakan opsi yang tersedia:");
            System.out.println("(1) Anggota Kelompok");
            System.out.println("(2) Definisi Program");
            System.out.println("(3) Fitur-fitur yang tersedia");
            System.out.println("(4) Materi atau Konsep yang digunakan");
            System.out.println("(5) Kembali ke menu awal");
            System.out.println("----------------------------------------");

            System.out.print("- Input: ");
            int inputOption = getIntInput();
            switch (inputOption) {
                case 1 -> credit.groupMembers();
                case 2 -> credit.programsDefinition();
                case 3 -> credit.availableFeatures();
                case 4 -> credit.conceptsUsed();
                case 5 -> {
                    ScreenCleaning.ClearScreen();
                    isRunning = false;
                }
                default -> FailuresView.showInvalidOptionMessage();
            }
        }
    }
}
