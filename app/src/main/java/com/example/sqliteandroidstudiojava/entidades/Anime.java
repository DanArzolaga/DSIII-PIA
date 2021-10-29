package com.example.sqliteandroidstudiojava.entidades;
public class Anime {

    private int id;
    private String name;
    private int year;
    private int episode;
    private String genre;
    private String description;
    private String url;

    public Anime() {
    }

    public Anime(String name, int year, int episode, String genre, String description, String url) {
        this.name = name;
        this.year = year;
        this.episode = episode;
        this.description = description;
        this.genre = genre;
        this.url = url;
    }

    public Anime(int id, String name, int year, int episode, String genre, String description, String url) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.episode = episode;
        this.genre = genre;
        this.description = description;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
