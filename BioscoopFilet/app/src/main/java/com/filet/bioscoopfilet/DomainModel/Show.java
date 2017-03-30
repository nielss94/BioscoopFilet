package com.filet.bioscoopfilet.DomainModel;

<<<<<<< HEAD
=======
import java.io.Serializable;
import java.util.Arrays;
>>>>>>> origin/master
import java.util.Date;

/**
 * Created by Niels on 3/28/2017.
 */

public class Show implements Serializable {

    private int showID;
    private Film film;
    private Theater theater;
    private Date time;
    private Boolean[] seats = new Boolean[150];

    public Show(int showID, Film film, Theater theater, Date time) {
        this.showID = showID;
        this.film = film;
        this.theater = theater;
        this.time = time;
    }

    public Show(int showID, Film film, Theater theater, Date time, Boolean[] seats) {
        this.showID = showID;
        this.film = film;
        this.theater = theater;
        this.time = time;
        this.seats = seats;
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

    public Boolean[] getSeats() {
        return seats;
    }

    public void setSeats(Boolean[] seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        String seatsAsText = "";
        for (int i = 0; i < getSeats().length; i++) {
            if(getSeats()[i] == true)
            {
                seatsAsText = seatsAsText + "1";
            }
            else{
                seatsAsText = seatsAsText + "0";
            }
        }
        return "Show{" +
                "showID=" + showID +
                ", film=" + film +
                ", theater=" + theater +
                ", time=" + time +
                ", seats=" + seatsAsText  +
                '}';
    }
}
