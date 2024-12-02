package models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Movie {
    private String title;
    private String director;
    private LocalDate releaseDate;
    private boolean onAir;
    private ArrayList<String> genres;
    private int rating;

    public Movie(
        String name, 
        String director, 
        LocalDate releaseDate,
        boolean onAir,
        ArrayList<String> genres,
        int rating
    ) {
        this.title = name;
        this.director = director;
        this.releaseDate = releaseDate;
        this.onAir = onAir;
        this.genres = genres;
        this.rating = rating;
    }

    public void setTitle(String newName) {
        this.title = newName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDirector(String newDirector) {
        this.director = newDirector;
    }

    public String getDirector() {
        return this.director;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate newReleaseDate) {
        this.releaseDate = newReleaseDate;
    }

    public void setOnAir(boolean onAir) {
        this.onAir = onAir;
    }

    public boolean getOnAir() {
        return this.onAir;
    }

    public void setRating(int newRating) {
        this.rating = newRating;
    }

    public int getRating() {
        return this.rating;
    }

    public void setGenres(ArrayList<String> newGenres) {
        this.genres = newGenres;
    }

    public ArrayList<String> getGenres() {
        return this.genres;
    }
}
