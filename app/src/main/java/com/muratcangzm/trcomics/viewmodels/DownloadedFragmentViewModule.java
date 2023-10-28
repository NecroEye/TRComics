package com.muratcangzm.trcomics.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.muratcangzm.trcomics.core.repository.RepoCore;
import com.muratcangzm.trcomics.saved_favorites.ComicRoomModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DownloadedFragmentViewModule extends AndroidViewModel {


    RepoCore repository;
    // TODO: 24.10.2023 fix dagger hilt

    private Executor executor = Executors.newSingleThreadExecutor();

    @Inject
    public DownloadedFragmentViewModule(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<List<ComicRoomModel>> comic = new MutableLiveData<>();
    final LiveData<List<ComicRoomModel>> getComics = comic;

    public void deleteComic(@NonNull final ComicRoomModel comicRoomModel){

        executor.execute(() -> { repository.deleteComic(comicRoomModel); });

    }

    public void addComic(@NonNull final ComicRoomModel comicRoomModel){

        executor.execute(() -> { repository.insertComic(comicRoomModel);});

    }

    public void searchComic(@NonNull final String word){

        if(word.trim().isEmpty()) return;

        executor.execute(() -> {

            LiveData<List<ComicRoomModel>> searching = repository.searchComic(word);


        });

    }

}
