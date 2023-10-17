package com.muratcangzm.trcomics.saved_favorites;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "comics")
public class ComicModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String author;
    public String coverUrl;
    public Date date;
    public String description;
    public ArrayList<String> episodes;
    public boolean favorite;
    public ArrayList<String> genres;
    @ColumnInfo(name = "comic_title")
    public String title;

}
