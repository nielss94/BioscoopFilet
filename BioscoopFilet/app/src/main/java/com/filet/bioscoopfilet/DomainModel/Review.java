package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;

/**
 * Created by Felix on 28-3-2017.
 */

public class Review implements Serializable {

    private int reviewID;
    private Film film;
    private Visitor visitor;
    private int score;
    private String description;

    public Review(int reviewID, Film film, Visitor visitor, int score, String description) {
        this.reviewID = reviewID;
        this.film = film;
        this.visitor = visitor;
        this.score = score;
        this.description = description;
    }

    public Review(Film film, Visitor visitor, int score, String description) {
        this.film = film;
        this.visitor = visitor;
        this.score = score;
        this.description = description;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
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
                "reviewID=" + reviewID +
                ", film=" + film +
                ", visitor=" + visitor +
                ", score=" + score +
                ", description='" + description + '\'' +
                '}';
    }
}
