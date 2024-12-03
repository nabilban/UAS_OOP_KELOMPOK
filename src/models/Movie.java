package models;

import enums.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Movie {
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
    private ArrayList<Genres> genres;
    private ArrayList<Audiance> seats;
    private final int MAX_SEATS = 30;

    public Movie(
            String name,
            String director,
            LocalDate releaseDate,
            boolean onAir,
            ArrayList<Genres> genres,
            int rating,
            ArrayList<Audiance> seats) {
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

    public void setGenres(ArrayList<Genres> newGenres) {
        this.genres = newGenres;
    }

    public ArrayList<Genres> getGenres() {
        return this.genres;
    }

    public void setSeats(ArrayList<Audiance> newSeats) {
        if (newSeats.size() <= MAX_SEATS) {
            this.seats = newSeats;
        } else {
            throw new IllegalArgumentException("Number of seats cannot exceed " + MAX_SEATS);
        }
    }

    public int getMaxSeats() {
        return this.MAX_SEATS;
    }

    public void addSeat(Audiance audiance) {
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
