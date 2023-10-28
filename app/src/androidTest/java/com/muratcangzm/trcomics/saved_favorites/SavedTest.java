package com.muratcangzm.trcomics.saved_favorites;


import androidx.test.filters.SmallTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import javax.inject.Inject;
import javax.inject.Named;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;


@SmallTest
@HiltAndroidTest
public class SavedTest {

    @Inject
    @Named("testDatabase")
    ComicDatabase database;
    private ComicDao dao;
    private ComicRoomModel comicRoomModel;


    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);


    @Before
    public void setup() {


        comicRoomModel = new ComicRoomModel("murat","sa", null,"sdf", null,false ,null,"super" );

        hiltRule.inject();

        dao = database.comicDao();


    }

    @After
    public void teardown(){

        database.close();

    }


    @Test
    public void insertComicTesting(){

    dao.insert(comicRoomModel);

    }

    @Test
    public void deleteComicTesting(){

     dao.delete(comicRoomModel);

    }

}
