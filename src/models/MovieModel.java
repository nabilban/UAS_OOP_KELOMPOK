package models;

import java.time.LocalDate;
import java.util.ArrayList;

public class MovieModel {
    private static int idCounter = 0;
    private int id;
    {
        id = ++idCounter;
    }
    private String title;
    private String director;
    private LocalDate releaseDate;
    private boolean onAir;
    private int rating;
    private ArrayList<String> genres;
    private ArrayList<AudianceModels> seats;
    private final int MAX_SEATS = 30;

    public MovieModel(
            String name,
            String director,
            LocalDate releaseDate,
            boolean onAir,
            ArrayList<String> genres,
            int rating,
            ArrayList<AudianceModels> seats) {
        this.title = name;
        this.director = director;
        this.releaseDate = releaseDate;
        this.onAir = onAir;
        this.genres = genres;
        this.rating = rating;
        setSeats(seats);
    }

    public int getId() {
        return id;
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

    public void setSeats(ArrayList<AudianceModels> newSeats) {
        if (newSeats.size() <= MAX_SEATS) {
            this.seats = newSeats;
        } else {
            throw new IllegalArgumentException("Number of seats cannot exceed " + MAX_SEATS);
        }
    }

    public int getMaxSeats() {
        return this.MAX_SEATS;
    }

    public void addSeat(AudianceModels audiance) {
        if (this.seats.size() < MAX_SEATS) {
            this.seats.add(audiance);
        } else {
            throw new IllegalArgumentException("Number of seats cannot exceed " + MAX_SEATS);
        }
    }

    public int getSeatsAvailable() {
        var data = MAX_SEATS - this.seats.size();
        return data;
    }
}
