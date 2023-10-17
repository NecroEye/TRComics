package com.muratcangzm.trcomics.saved_favorites;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ComicModel.class}, version = 1)
public abstract class ComicDatabase extends RoomDatabase {

  public abstract ComicDao comicDao();


}
