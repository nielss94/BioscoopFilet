package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;

/**
 * Created by Niels on 3/28/2017.
 */
public class Theater implements Serializable {

    private int theaterID;
    private Cinema cinema;
    private int numberOfSeats;

    public Theater(int theaterID, Cinema cinema, int numberOfSeats) {
        this.theaterID = theaterID;
        this.cinema = cinema;
        this.numberOfSeats = numberOfSeats;
    }

    public Theater(Cinema cinema, int numberOfSeats) {
        this.cinema = cinema;
        this.numberOfSeats = numberOfSeats;
    }

    public int getTheaterID() {
        return theaterID;
    }

    public void setTheaterID(int theaterID) {
        this.theaterID = theaterID;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


    @Override
    public String toString() {
        return "Theater{" +
                "theaterID=" + theaterID +
                ", cinema=" + cinema +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
