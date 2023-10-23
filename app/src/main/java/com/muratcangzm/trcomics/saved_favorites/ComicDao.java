package com.muratcangzm.trcomics.saved_favorites;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import javax.inject.Inject;

@Dao
public interface ComicDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ComicModel comicModel);
    @Insert
    void insertAll(ComicModel... comicModels);

    @Update
    void updateComics(ComicModel... models);

    @Delete
    void delete(ComicModel model);

    @Query("SELECT * FROM comics")
    LiveData<List<ComicModel>> getAll();

    @Query("SELECT * FROM comics WHERE comic_title LIKE :search")
    LiveData<List<ComicModel>> findComicByItsTitle(String search);
}
