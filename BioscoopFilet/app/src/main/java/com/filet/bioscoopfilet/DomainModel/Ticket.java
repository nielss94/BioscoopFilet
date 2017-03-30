package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;

/**
 * Created by Niels on 3/28/2017.
 */

public class Ticket implements Serializable {

    private String qrCode;
    private int visitorID;
    private int showID;
    private int seat;

    public Ticket(String qrCode, int visitorID, int showID, int seat) {
        this.qrCode = qrCode;
        this.visitorID = visitorID;
        this.showID = showID;
        this.seat = seat;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getVisitorID() {
        return visitorID;
    }

    public void setVisitorID(int visitorID) {
        this.visitorID = visitorID;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "qrCode='" + qrCode + '\'' +
                ", visitorID=" + visitorID +
                ", showID=" + showID +
                ", seat=" + seat +
                '}';
    }
}
