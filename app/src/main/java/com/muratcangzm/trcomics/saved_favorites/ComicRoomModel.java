package com.muratcangzm.trcomics.saved_favorites;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "comics")
public class ComicRoomModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String author;
    public String coverUrl;
    public Date date;
    public String description;
    @Nullable
    public ArrayList<String> episodes;
    public boolean favorite;
    @Nullable
    public ArrayList<String> genres;
    @ColumnInfo(name = "comic_title")
    public String title;


    public ComicRoomModel(String author, String coverUrl,
                          @Nullable Date date, String description, @Nullable ArrayList<String> episodes, boolean favorite,
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
}
