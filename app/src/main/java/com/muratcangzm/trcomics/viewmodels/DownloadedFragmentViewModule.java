package com.muratcangzm.trcomics.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.muratcangzm.trcomics.core.repository.RepoCore;
import com.muratcangzm.trcomics.saved_favorites.ComicModel;

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

    private MutableLiveData<List<ComicModel>> comic = new MutableLiveData<>();
    final LiveData<List<ComicModel>> getComics = comic;

    public void deleteComic(@NonNull final ComicModel comicModel){

        executor.execute(() -> { repository.deleteComic(comicModel); });

    }

    public void addComic(@NonNull final ComicModel comicModel){

        executor.execute(() -> { repository.insertComic(comicModel);});

    }

    public void searchComic(@NonNull final String word){

        if(word.trim().isEmpty()) return;

        executor.execute(() -> {

            LiveData<List<ComicModel>> searching = repository.searchComic(word);


        });

    }

}
