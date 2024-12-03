package initialize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Genres;
import models.Audiance;
import models.Makanan;
import models.Minuman;
import models.Movie;
import services.CinemaManagement;

public class Initialize {

    public static CinemaManagement initializeData() {
        CinemaManagement cinemaManagement = new CinemaManagement();
        cinemaManagement.items.add(new Makanan("PopCorn", 30000, 10));
        cinemaManagement.items.add(new Makanan("Nachos", 25000, 15));
        cinemaManagement.items.add(new Makanan("Hot Dog", 20000, 0));
        cinemaManagement.items.add(new Minuman("Soda", 15000, 0));
        cinemaManagement.items.add(new Minuman("Coffee", 20000, 25));
        cinemaManagement.items.add(new Minuman("Tea", 15000, 20));
        cinemaManagement.items.add(new Minuman("Juice", 25000, 0));
        cinemaManagement.movies.add(
                new Movie(
                        "Star Wars: Episode III - Revenge of the Sith",
                        "George Lucas",
                        LocalDate.of(2005, 5, 12),
                        true,
                        new ArrayList<>(List.of(
                                Genres.Action,
                                Genres.Adventure,
                                Genres.Fantasy,
                                Genres.SciFi)),
                        8,
                        new ArrayList<>(getPredefinedAudience())));
        cinemaManagement.movies.add(
                new Movie(
                        "Frozen",
                        "Chris Buck, Jennifer Lee",
                        LocalDate.of(2013, 11, 29),
                        false,
                        new ArrayList<>(List.of(
                                Genres.Animation,
                                Genres.Adventure,
                                Genres.Family)),
                        8,
                        new ArrayList<>(getPredefinedAudience())));

        return cinemaManagement;
    }

    private static ArrayList<Audiance> getPredefinedAudience() {
        return new ArrayList<>(List.of(
                new Audiance(
                        "Alice Johnson",
                        25,
                        "Female",
                        true,
                        new ArrayList<>(List.of(
                                new Makanan("PopCorn", 30000, 1),
                                new Minuman("Coffee", 20000, 1)))),
                new Audiance(
                        "Bob Smith",
                        30,
                        "Male",
                        false,
                        new ArrayList<>(List.of(
                                new Makanan("Nachos", 25000, 2)))),
                new Audiance(
                        "Catherine Lee",
                        18,
                        "Female",
                        true,
                        new ArrayList<>(List.of(
                                new Minuman("Tea", 15000, 1)))),
                new Audiance(
                        "David Brown",
                        40,
                        "Male",
                        false,
                        new ArrayList<>()),
                new Audiance(
                        "Evelyn White",
                        22,
                        "Female",
                        true,
                        new ArrayList<>(List.of(
                                new Minuman("Juice", 25000, 1),
                                new Minuman("Tea", 15000, 1)))),
                new Audiance(
                        "Frank Green",
                        35,
                        "Male",
                        true,
                        new ArrayList<>(List.of(
                                new Makanan("Hot Dog", 20000, 1))))));
    }
}
