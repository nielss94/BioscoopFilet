package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;

/**
 * Created by Niels on 3/28/2017.
 */

public class Ticket implements Serializable {

    private String qrCode;
    private Visitor visitor;
    private Show showID;
    private int seat;

    public Ticket(String qrCode, Visitor visitor, Show showID, int seat) {
        this.qrCode = qrCode;
        this.visitor = visitor;
        this.showID = showID;
        this.seat = seat;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Show getShowID() {
        return showID;
    }

    public void setShowID(Show showID) {
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
                ", visitor=" + visitor +
                ", showID=" + showID +
                ", seat=" + seat +
                '}';
    }
}
