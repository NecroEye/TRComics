package com.muratcangzm.trcomics.core;


import android.content.Context;

import androidx.room.Room;

import com.muratcangzm.trcomics.saved_favorites.ComicDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class ComicModule {


    @Singleton
    public static void injectRoom(Context context) {

        Room.databaseBuilder(context, ComicDatabase.class, "SavedDB")
                .createFromAsset("database/comic.db")
                .build();

    }

    @Singleton
    public static void injectDao(ComicDatabase comicDatabase) {
        comicDatabase.comicDao();
    }

}
