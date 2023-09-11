package com.muratcangzm.trcomics.recyclerView;

import android.graphics.Bitmap;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class CardViewModel {

    private int mangaId;
    private Integer image, episode_count;
    private String title;
    private ArrayList<String> last_episodes;
    private Date uploadDate;
    private Double rating;
    private String[] genres;
    private int[] images;
    private ArrayList<String> episodes;
    private Boolean isNew;


    public CardViewModel(int mangaId, @DrawableRes Integer image, Integer episode_count, String title,
                         @Nullable ArrayList<String> last_episodes,
                         @Nullable Date uploadDate, @Nullable Double rating,
                         @Nullable int[] images,
                         @Nullable String[] genres,
                         @Nullable ArrayList<String> episodes,
                         @Nullable Boolean isNew) {

        this.mangaId = mangaId;
        this.image = image;
        this.episode_count = episode_count;
        this.title = title;
        this.last_episodes = last_episodes;
        this.uploadDate = uploadDate;
        this.rating = rating;
        this.images = images;
        this.genres = genres;
        this.episodes = episodes;
        this.isNew = isNew;

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

    public ArrayList<String> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<String> episodes) {
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
}
