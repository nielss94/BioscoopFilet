package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;

/**
 * Created by Felix on 28-3-2017.
 */

public class Review implements Serializable {

    private int reviewID;
    private int filmAPIID;
    private Visitor visitor;
    private String name;
    private int score;
    private String description;

    public Review(int reviewID, int filmAPIID, Visitor visitor, int score, String description) {
        this.reviewID = reviewID;
        this.filmAPIID = filmAPIID;
        this.visitor = visitor;
        this.score = score;
        this.description = description;
    }

    public Review(int filmAPIID, Visitor visitor, int score, String description) {
        this.filmAPIID = filmAPIID;
        this.visitor = visitor;
        this.score = score;
        this.description = description;
    }

    public Review(int filmAPIID, String name, int score, String description) {
        this.filmAPIID = filmAPIID;
        this.name = name;
        this.score = score;
        this.description = description;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getFilmAPIID() {
        return filmAPIID;
    }

    public void setFilmAPIID(int filmAPIID) {
        this.filmAPIID = filmAPIID;
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
                ", filmAPIID=" + filmAPIID +
                ", visitor=" + visitor +
                ", score=" + score +
                ", description='" + description + '\'' +
                '}';
    }
}
