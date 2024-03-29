package com.filet.bioscoopfilet.DomainModel;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Niels on 3/28/2017.
 */

public class Film implements Serializable, Comparable<Film>{

    private int filmID;
    private int filmAPIID;
    private Cinema cinema;
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

    public static final Comparator<Film> ASCENDING_AGE = new Comparator<Film>(){
        public int compare(Film f, Film f1){
            return f.age - f1.age;
        }
    };

    public static final Comparator<Film> DESCENDING_AGE = new Comparator<Film>(){
        public int compare(Film f, Film f1){
            return f1.age - f.age;
        }
    };

    public static final Comparator<Film> DESCENDING_SCORE = new Comparator<Film>(){
        public int compare(Film f, Film f1){
            double film = Double.parseDouble(f.getIMDBScore());
            double film1 = Double.parseDouble(f1.getIMDBScore());
            return Double.compare(film1, film);
        }
    };

    public static final Comparator<Film> ASCENDING_SCORE = new Comparator<Film>(){
        public int compare(Film f, Film f1){
            double film = Double.parseDouble(f.getIMDBScore());
            double film1 = Double.parseDouble(f1.getIMDBScore());
            return Double.compare(film, film1);
        }
    };

    @Override
    public int compareTo(Film f) {
        return (this.title).compareTo(f.title);
    }

    public Film(int filmID, int filmAPIID, Cinema cinema, String title, String version, String language,
                String releaseDate, String genre, int length, int age, String description,
                String IMDBUrl, String IMDBScore, String trailerURL, String posterURL, String director) {
        this.filmAPIID = filmAPIID;
        this.filmID = filmID;
        this.cinema = cinema;
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

    public Film(int filmAPIID, Cinema cinema, String title, String version, String language,
                String releaseDate, String genre, int length, int age, String description,
                String IMDBUrl, String IMDBScore, String trailerURL, String posterURL, String director) {
        this.filmAPIID = filmAPIID;
        this.cinema = cinema;
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

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
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

    public int getFilmAPIID() {
        return filmAPIID;
    }

    public void setFilmAPIID(int filmAPIID) {
        this.filmAPIID = filmAPIID;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmID=" + filmID +
                ", filmAPIID=" + filmAPIID +
                ", cinema=" + cinema +
                ", title='" + title + '\'' +
                ", version='" + version + '\'' +
                ", language='" + language + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", genre='" + genre + '\'' +
                ", length=" + length +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", IMDBUrl='" + IMDBUrl + '\'' +
                ", IMDBScore='" + IMDBScore + '\'' +
                ", trailerURL='" + trailerURL + '\'' +
                ", posterURL='" + posterURL + '\'' +
                ", director='" + director + '\'' +
                ", actors=" + actors +
                '}';
    }
}
