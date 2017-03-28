package com.filet.bioscoopfilet.DomainModel;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Niels on 3/28/2017.
 */

public class Show {

    private int showID;
    private int filmID;
    private int theaterID;
    private Date time;
    private Boolean[] seats = new Boolean[150];

    public Show(int showID, int filmID, int theaterID, Date time) {
        this.showID = showID;
        this.filmID = filmID;
        this.theaterID = theaterID;
        this.time = time;
    }

    public Show(int showID, int filmID, int theaterID, Date time, Boolean[] seats) {
        this.showID = showID;
        this.filmID = filmID;
        this.theaterID = theaterID;
        this.time = time;
        this.seats = seats;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getTheaterID() {
        return theaterID;
    }

    public void setTheaterID(int theaterID) {
        this.theaterID = theaterID;
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
                ", filmID=" + filmID +
                ", theaterID=" + theaterID +
                ", time=" + time +
                ", seats=" + seatsAsText  +
                '}';
    }
}
