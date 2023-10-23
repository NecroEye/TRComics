package com.muratcangzm.trcomics.core;

import android.content.Context;

import androidx.room.Room;

import com.muratcangzm.trcomics.saved_favorites.ComicDatabase;

import org.junit.Rule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ActivityComponent.class)
public class ComicModuleTest {



    @Provides
    @Named("testDatabase")
    public static ComicDatabase injectInMemoryRoom(@ApplicationContext Context context) {

       return Room.inMemoryDatabaseBuilder(context, ComicDatabase.class)
                .allowMainThreadQueries()
                .build();

    }

}
