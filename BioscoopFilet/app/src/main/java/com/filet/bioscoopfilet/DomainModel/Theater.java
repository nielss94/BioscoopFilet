package com.filet.bioscoopfilet.DomainModel;

/**
 * Created by Niels on 3/28/2017.
 */
public class Theater {

    private int theaterID;
    private int cinemaID;
    private int numberOfSeats;

    public Theater(int theaterID, int cinemaID, int numberOfSeats) {
        this.theaterID = theaterID;
        this.cinemaID = cinemaID;
        this.numberOfSeats = numberOfSeats;
    }

    public int getTheaterID() {
        return theaterID;
    }

    public void setTheaterID(int theaterID) {
        this.theaterID = theaterID;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
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
                ", cinemaID=" + cinemaID +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
