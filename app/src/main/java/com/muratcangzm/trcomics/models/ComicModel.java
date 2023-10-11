package com.muratcangzm.trcomics.models;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class ComicModel {


    private String author;
    private String coverUrl;
    private Date date;
    private String description;
    private ArrayList<String> episodes;
    private boolean favorite;
    private ArrayList<String> genres;
    private String title;


    public ComicModel(){

    }

    public ComicModel(String author, String coverUrl, Date date,
                      String description, @Nullable ArrayList<String> episodes, boolean favorite,
                      @Nullable ArrayList<String> genres, String title) {
        this.author = author;
        this.coverUrl = coverUrl;
        this.date = date;
        this.description = description;
        this.episodes = episodes;
        this.favorite = favorite;
        this.genres = genres;
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getEpisodes() {
        return episodes;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public String getTitle() {
        return title;
    }

    public void setEpisodes(ArrayList<String> episodes) {
        this.episodes = episodes;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
