package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Niels on 3/28/2017.
 */

public class Show implements Serializable {

    private int showID;
    private int filmAPIID;
    private Theater theater;
    private Date time;
    private String seats;

    public Show(int filmAPIID, Theater theater, Date time, String seats) {
        this.filmAPIID = filmAPIID;
        this.theater = theater;
        this.time = time;
        this.seats = seats;
    }

    public Show(int filmAPIID, Theater theater, Date time) {
        this.filmAPIID = filmAPIID;
        this.theater = theater;
        this.time = time;
    }

    public Show(int showID, int filmAPIID, Theater theater, Date time, String seats) {
        this.showID = showID;
        this.filmAPIID = filmAPIID;
        this.theater = theater;
        this.time = time;
        this.seats = seats;
    }

    public Show(int showID, int filmAPIID, Theater theater, Date time) {
        this.showID = showID;
        this.filmAPIID = filmAPIID;
        this.theater = theater;
        this.time = time;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public int getFilmAPIID() {
        return filmAPIID;
    }

    public void setFilmAPIID(int filmAPIID) {
        this.filmAPIID = filmAPIID;
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
                ", filmAPIID=" + filmAPIID +
                ", theater=" + theater +
                ", time=" + time +
                ", seats=" + seats  +
                '}';
    }
}
