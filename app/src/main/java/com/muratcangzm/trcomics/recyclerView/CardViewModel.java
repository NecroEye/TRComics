package com.muratcangzm.trcomics.recyclerView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class CardViewModel {

    private int mangaId;
    private Integer image, episode_count;
    private String title;
    private String description;
    private String Author;
    private ArrayList<String> last_episodes;
    private Date uploadDate;
    private Double rating;
    private String[] genres;
    private int[] images;
    private String[] episodes;
    private Boolean isNew;
    private Boolean isFav;


    public CardViewModel(int mangaId, @DrawableRes Integer image, Integer episode_count, @NonNull String title,
                         @Nullable String description,
                         @Nullable String Author,
                         @Nullable ArrayList<String> last_episodes,
                         @Nullable Date uploadDate, @Nullable Double rating,
                         @Nullable int[] images,
                         @Nullable String[] genres,
                         @Nullable String[] episodes,
                         @Nullable Boolean isNew,
                         @Nullable Boolean isFav) {

        this.mangaId = mangaId;
        this.image = image;
        this.episode_count = episode_count;
        this.title = title;
        this.description = description;
        this.Author = Author;
        this.last_episodes = last_episodes;
        this.uploadDate = uploadDate;
        this.rating = rating;
        this.images = images;
        this.genres = genres;
        this.episodes = episodes;
        this.isNew = isNew;
        this.isFav = isFav;

    }

    public int getMangaId() {
        return mangaId;
    }

    public Integer getImage() {
        return image;
    }

    public Integer getEpisode_count() {
        return episode_count;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public ArrayList<String> getLast_episodes() {
        return last_episodes;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public double getRating() {
        return rating;
    }

    public boolean isNew() {
        return isNew;
    }

    public String[] getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String[] episodes) {
        this.episodes = episodes;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public int[] getImages() {
        return images;
    }

    public void setImages(int[] images) {
        this.images = images;
    }


    public Boolean getFav() {
        return isFav;
    }

    public void setFav(Boolean fav) {
        isFav = fav;
    }
}
