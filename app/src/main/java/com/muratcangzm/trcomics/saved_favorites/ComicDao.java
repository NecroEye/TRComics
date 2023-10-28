package com.muratcangzm.trcomics.saved_favorites;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ComicDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ComicRoomModel comicRoomModel);
    @Insert
    void insertAll(ComicRoomModel... comicRoomModels);

    @Update
    void updateComics(ComicRoomModel... models);

    @Delete
    void delete(ComicRoomModel model);

    @Query("SELECT * FROM ComicRoomModel")
    LiveData<List<ComicRoomModel>> getAll();

    @Query("SELECT * FROM ComicRoomModel WHERE comic_title LIKE :search")
    LiveData<List<ComicRoomModel>> findComicByItsTitle(String search);
}
