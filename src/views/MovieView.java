package views;

import java.util.ArrayList;

import models.MovieModel;
import utils.ScreenCleaning;

public class MovieView {
    public static void displayMovieList(ArrayList<MovieModel> movies) {

        ScreenCleaning.ClearScreen();
        System.out.println("---------------------");
        System.out.println("| == Daftar Film == |");
        System.out.println("---------------------");

        if (movies.isEmpty()) {
            FailuresView.showNoFilmAvailableMessage();
            return;
        }

        System.out.println("---------------------------------------------");
        for (MovieModel movie : movies) {
            System.out.println("- ID      : " + movie.getId());
            System.out.println("- Title   : " + movie.getTitle());
            System.out.println("- Director: " + movie.getDirector());
            System.out.println("- On air  : " + movie.getOnAir());
            System.out.println("- Genres  : " + movie.getGenres());
            System.out.println("- Rating  : " + movie.getRating());
            System.out.println("---------------------------------------------");
        }
    }

    public static void displayPlayingMovies(ArrayList<MovieModel> movies) {
        ScreenCleaning.ClearScreen();

        if (movies.isEmpty()) {
            for (MovieModel movie : movies) {
                var onAirMovies = new ArrayList<>();
                if (movie.getOnAir()) {
                    onAirMovies.add(movie);
                }
                if (onAirMovies.isEmpty()) {
                    FailuresView.showNoFilmPlayedMessage();
                    return;
                }
            }
            FailuresView.showNoFilmAvailableMessage();
            return;
        }

        ScreenCleaning.ClearScreen();
        System.out.println("----------------------------------------");
        System.out.println("| == Daftar Film yang Sedang Tayang == |");
        System.out.println("----------------------------------------");

        for (MovieModel movie : movies) {
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
}
