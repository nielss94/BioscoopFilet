package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;

/**
 * Created by Felix on 28-3-2017.
 */

public class Review implements Serializable {

    private int ID;
    private int filmID;
    private int visitorID;
    private int score;
    private String description;

    public Review(int ID, int filmID, int visitorID, int score, String description) {
        this.ID = ID;
        this.filmID = filmID;
        this.visitorID = visitorID;
        this.score = score;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getVisitorID() {
        return visitorID;
    }

    public void setVisitorID(int visitorID) {
        this.visitorID = visitorID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Review{" +
                "ID=" + ID +
                ", filmID=" + filmID +
                ", visitorID=" + visitorID +
                ", score=" + score +
                ", description='" + description + '\'' +
                '}';
    }
}
