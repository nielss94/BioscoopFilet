package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Niels on 3/28/2017.
 */

public class Show implements Serializable {

    private int showID;
    private Film film;
    private Theater theater;
    private Date time;
    private String seats;

    public Show(Film film, Theater theater, Date time, String seats) {
        this.film = film;
        this.theater = theater;
        this.time = time;
        this.seats = seats;
    }

    public Show(Film film, Theater theater, Date time) {
        this.film = film;
        this.theater = theater;
        this.time = time;
    }

    public Show(int showID, Film film, Theater theater, Date time, String seats) {
        this.showID = showID;
        this.film = film;
        this.theater = theater;
        this.time = time;
        this.seats = seats;
    }

    public Show(int showID, Film film, Theater theater, Date time) {
        this.showID = showID;
        this.film = film;
        this.theater = theater;
        this.time = time;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {

        return "Show{" +
                "showID=" + showID +
                ", film=" + film +
                ", theater=" + theater +
                ", time=" + time +
                ", seats=" + seats  +
                '}';
    }
}
