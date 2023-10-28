package com.muratcangzm.trcomics.core.repository;

import androidx.lifecycle.LiveData;
import com.muratcangzm.trcomics.saved_favorites.ComicRoomModel;
import java.util.List;

public interface RepoCore {


    void insertComic(ComicRoomModel comicRoomModel);


    void deleteComic(ComicRoomModel comicRoomModel);

    LiveData<List<ComicRoomModel>> getComic();

    LiveData<List<ComicRoomModel>> searchComic(String search);


}
