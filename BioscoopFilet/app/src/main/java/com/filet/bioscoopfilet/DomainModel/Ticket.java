package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;

/**
 * Created by Niels on 3/28/2017.
 */

public class Ticket implements Serializable {

    private int qrCode;
    private Visitor visitor;
    private Show show;
    private int seat;

    public Ticket(int qrCode, Visitor visitor, Show show, int seat) {
        this.qrCode = qrCode;
        this.visitor = visitor;
        this.show = show;
        this.seat = seat;
    }

    public int getQrCode() {
        return qrCode;
    }

    public void setQrCode(int qrCode) {
        this.qrCode = qrCode;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
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
                ", show=" + show +
                ", seat=" + seat +
                '}';
    }
}
