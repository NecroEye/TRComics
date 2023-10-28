package com.muratcangzm.trcomics.saved_favorites;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ComicRoomModel.class}, version = 1)
public abstract class ComicDatabase extends RoomDatabase {

  public abstract ComicDao comicDao();


}
