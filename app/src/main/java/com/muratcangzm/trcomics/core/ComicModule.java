package com.muratcangzm.trcomics.core;


import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.muratcangzm.trcomics.core.repository.ComicRepo;
import com.muratcangzm.trcomics.saved_favorites.ComicDao;
import com.muratcangzm.trcomics.saved_favorites.ComicDatabase;
import com.muratcangzm.trcomics.viewmodels.ComicViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class ComicModule {


    @Singleton
    @Provides
    public static synchronized ComicDatabase injectRoom(Context context) {

        return Room.databaseBuilder(context, ComicDatabase.class, "SavedDB")
                .createFromAsset("database/comic.db")
                .fallbackToDestructiveMigration()
                .build();

    }

    @Singleton
    @Provides
    public static synchronized ComicDao injectDao(ComicDatabase comicDatabase) {
        return comicDatabase.comicDao();
    }

    @Singleton
    @Provides
    public static synchronized ComicRepo injectComicRepo(ComicDao comicDao) {
        return new ComicRepo(comicDao);
    }

    @Singleton
    @Provides
    public static synchronized ComicViewModel provideComicViewModel(Application application) {
        return new ViewModelProvider.AndroidViewModelFactory(application).create(ComicViewModel.class);
    }


}
