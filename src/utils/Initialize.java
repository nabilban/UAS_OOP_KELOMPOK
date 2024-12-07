package utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controllers.CinemaManagement;
import models.AudianceModels;
import models.FoodsModel;
import models.DrinksModel;
import models.MovieModel;

public class Initialize {

        public static CinemaManagement initializeData() {
                CinemaManagement cinemaManagement = new CinemaManagement();
                cinemaManagement.items.add(new FoodsModel("PopCorn", 30000, 10));
                cinemaManagement.items.add(new FoodsModel("Nachos", 25000, 15));
                cinemaManagement.items.add(new FoodsModel("Hot Dog", 20000, 0));
                cinemaManagement.items.add(new DrinksModel("Soda", 15000, 0));
                cinemaManagement.items.add(new DrinksModel("Coffee", 20000, 25));
                cinemaManagement.items.add(new DrinksModel("Tea", 15000, 20));
                cinemaManagement.items.add(new DrinksModel("Juice", 25000, 0));
                cinemaManagement.movies.add(
                                new MovieModel(
                                                "Star Wars: Episode I - The Phantom Menace",
                                                "George Lucas",
                                                LocalDate.of(1999, 5, 19),
                                                true,
                                                new ArrayList<String>(List.of(
                                                                "Action",
                                                                "Advanture",
                                                                "Fantasy",
                                                                "Sci-Fi")),
                                                6,
                                                new ArrayList<>(getPredefinedAudience())));

                cinemaManagement.movies.add(
                                new MovieModel(
                                                "Star Wars: Episode II - Attack of the Clones",
                                                "George Lucas",
                                                LocalDate.of(2002, 5, 16),
                                                true,
                                                new ArrayList<>(List.of(
                                                                "Action",
                                                                "Advanture",
                                                                "Fantasy",
                                                                "Sci-Fi")),
                                                6,
                                                new ArrayList<>(getPredefinedAudience())));

                cinemaManagement.movies.add(
                                new MovieModel(
                                                "Star Wars: Episode III - Revenge of the Sith",
                                                "George Lucas",
                                                LocalDate.of(2005, 5, 12),
                                                false,
                                                new ArrayList<>(List.of(
                                                                "Action",
                                                                "Advanture",
                                                                "Fantasy",
                                                                "Sci-Fi")),
                                                8,
                                                new ArrayList<>(getPredefinedAudience())));

                cinemaManagement.movies.add(
                                new MovieModel(
                                                "Frozen",
                                                "Chris Buck, Jennifer Lee",
                                                LocalDate.of(2013, 11, 29),
                                                true,
                                                new ArrayList<>(List.of(
                                                                "Animation",
                                                                "Advanture",
                                                                "Family")),
                                                8,
                                                new ArrayList<>(getPredefinedAudience())));

                cinemaManagement.movies.add(
                                new MovieModel(
                                                "Frozen II",
                                                "Chris Buck, Jennifer Lee",
                                                LocalDate.of(2019, 11, 20),
                                                true,
                                                new ArrayList<>(List.of(
                                                                "Animation",
                                                                "Advanture",
                                                                "Family")),
                                                8,
                                                new ArrayList<>(getPredefinedAudience())));

                cinemaManagement.movies.add(
                                new MovieModel(
                                                "Pirates of Silicon Valley",
                                                "Martyn Burke",
                                                LocalDate.of(1999, 06, 20),
                                                false,
                                                new ArrayList<>(List.of(
                                                                "Animation",
                                                                "Advanture",
                                                                "Family")),
                                                7,
                                                new ArrayList<>(getPredefinedAudience())));

                cinemaManagement.movies.add(
                                new MovieModel(
                                                "The Imitationn Game",
                                                "Morten Tyldum",
                                                LocalDate.of(2015, 01, 22),
                                                false,
                                                new ArrayList<>(List.of(
                                                                "Animation",
                                                                "Advanture",
                                                                "Family")),
                                                8,
                                                new ArrayList<>(getPredefinedAudience())));

                return cinemaManagement;
        }

        public static ArrayList<AudianceModels> getPredefinedAudience() {
                return new ArrayList<>(List.of(
                                new AudianceModels(
                                                "Alice Johnson",
                                                25,
                                                "Female",
                                                true,
                                                new ArrayList<>(List.of(
                                                                new FoodsModel("PopCorn", 30000, 1),
                                                                new DrinksModel("Coffee", 20000, 1)))),
                                new AudianceModels(
                                                "Bob Smith",
                                                30,
                                                "Male",
                                                false,
                                                new ArrayList<>(List.of(
                                                                new FoodsModel("Nachos", 25000, 2)))),
                                new AudianceModels(
                                                "Catherine Lee",
                                                18,
                                                "Female",
                                                true,
                                                new ArrayList<>(List.of(
                                                                new DrinksModel("Tea", 15000, 1)))),
                                new AudianceModels(
                                                "David Brown",
                                                40,
                                                "Male",
                                                false,
                                                new ArrayList<>()),
                                new AudianceModels(
                                                "Evelyn White",
                                                22,
                                                "Female",
                                                true,
                                                new ArrayList<>(List.of(
                                                                new DrinksModel("Juice", 25000, 1),
                                                                new DrinksModel("Tea", 15000, 1)))),
                                new AudianceModels(
                                                "Frank Green",
                                                35,
                                                "Male",
                                                true,
                                                new ArrayList<>(List.of(
                                                                new FoodsModel("Hot Dog", 20000, 1))))));
        }
}
