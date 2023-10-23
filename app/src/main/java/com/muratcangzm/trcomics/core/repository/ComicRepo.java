package com.muratcangzm.trcomics.core.repository;

import androidx.lifecycle.LiveData;
import com.muratcangzm.trcomics.saved_favorites.ComicDao;
import com.muratcangzm.trcomics.saved_favorites.ComicModel;
import java.util.List;
import javax.inject.Inject;

public class ComicRepo implements RepoCore {


    private ComicDao comicDao;

    @Inject
    public ComicRepo(final ComicDao comicDao){

        this.comicDao = comicDao;

    }

    @Override
    public void insertComic(ComicModel comicModel) {

      comicDao.insert(comicModel);

    }

    @Override
    public void deleteComic(ComicModel comicModel) {

        comicDao.delete(comicModel);

    }

    @Override
    public LiveData<List<ComicModel>> getComic() {
        return comicDao.getAll();
    }

    @Override
    public LiveData<List<ComicModel>> searchComic(String search) {
        return comicDao.findComicByItsTitle(search);
    }
}
