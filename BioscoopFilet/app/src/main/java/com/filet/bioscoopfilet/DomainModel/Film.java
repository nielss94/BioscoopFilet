package com.filet.bioscoopfilet.DomainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Niels on 3/28/2017.
 */

public class Film implements Serializable{

    private int filmID;
    private int cinemaID;
    private String title;
    private String version;
    private String language;
    private String releaseDate;
    private String genre;
    private int length;
    private int age;
    private String description;
    private String IMDBUrl;
    private String IMDBScore;
    private String trailerURL;
    private String posterURL;
    private String director;
    private ArrayList<Actor> actors;

    public Film(int filmID, int cinemaID, String title, String version, String language,
                String releaseDate, String genre, int length, int age, String description,
                String IMDBUrl, String IMDBScore, String trailerURL, String posterURL, String director) {
        this.filmID = filmID;
        this.cinemaID = cinemaID;
        this.title = title;
        this.version = version;
        this.language = language;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.length = length;
        this.age = age;
        this.description = description;
        this.IMDBUrl = IMDBUrl;
        this.IMDBScore = IMDBScore;
        this.trailerURL = trailerURL;
        this.posterURL = posterURL;
        this.director = director;
    }

    public void addActor(Actor actor)
    {
        actors.add(actor);
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIMDBUrl() {
        return IMDBUrl;
    }

    public void setIMDBUrl(String IMDBUrl) {
        this.IMDBUrl = IMDBUrl;
    }

    public String getIMDBScore() {
        return IMDBScore;
    }

    public void setIMDBScore(String IMDBScore) {
        this.IMDBScore = IMDBScore;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }
}
