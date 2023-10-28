package com.muratcangzm.trcomics.core.repository;

import androidx.lifecycle.LiveData;
import com.muratcangzm.trcomics.saved_favorites.ComicDao;
import com.muratcangzm.trcomics.saved_favorites.ComicRoomModel;
import java.util.List;
import javax.inject.Inject;

public class ComicRepo implements RepoCore {


    private ComicDao comicDao;

    @Inject
    public ComicRepo(final ComicDao comicDao){

        this.comicDao = comicDao;

    }

    @Override
    public void insertComic(ComicRoomModel comicRoomModel) {

      comicDao.insert(comicRoomModel);

    }

    @Override
    public void deleteComic(ComicRoomModel comicRoomModel) {

        comicDao.delete(comicRoomModel);

    }

    @Override
    public LiveData<List<ComicRoomModel>> getComic() {
        return comicDao.getAll();
    }

    @Override
    public LiveData<List<ComicRoomModel>> searchComic(String search) {
        return comicDao.findComicByItsTitle(search);
    }
}
