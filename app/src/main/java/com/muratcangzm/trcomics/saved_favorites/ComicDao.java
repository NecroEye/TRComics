package com.muratcangzm.trcomics.saved_favorites;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ComicDao {


    @Insert
    void insertAll(ComicModel... comicModels);

    @Update
    void updateComics(ComicModel... models);

    @Delete
    void delete(ComicModel model);

    @Query("SELECT * FROM comics")
    List<ComicModel> getAll();

    @Query("SELECT * FROM comics WHERE comic_title LIKE :search")
    List<ComicModel> findComicByItsTitle(String search);
}
