package com.muratcangzm.trcomics.core.repository;

import androidx.lifecycle.LiveData;
import com.muratcangzm.trcomics.saved_favorites.ComicModel;
import java.util.List;

public interface RepoCore {


    void insertComic(ComicModel comicModel);


    void deleteComic(ComicModel comicModel);

    LiveData<List<ComicModel>> getComic();

    LiveData<List<ComicModel>> searchComic(String search);


}
