package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;

/**
 * Created by Niels on 3/28/2017.
 */

public class Feedback implements Serializable {

    private int ID;
    private int visitorID;
    private String description;

    public Feedback(int ID, int visitorID, String description) {
        this.ID = ID;
        this.visitorID = visitorID;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getVisitorID() {
        return visitorID;
    }

    public void setVisitorID(int visitorID) {
        this.visitorID = visitorID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "ID=" + ID +
                ", visitorID=" + visitorID +
                ", description='" + description + '\'' +
                '}';
    }
}
