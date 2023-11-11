package com.muratcangzm.trcomics.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muratcangzm.trcomics.models.ComicModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ComicViewModel extends ViewModel {


    private MutableLiveData<ComicModel> comic = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ComicModel>> comics = new MutableLiveData<>();


    @Inject
    public ComicViewModel() {

    }

    public LiveData<ComicModel> getComicModel() {

        return comic;
    }

    public LiveData<ArrayList<ComicModel>> getComicModels() {
        return comics;
    }

    public void addComic(ComicModel comics) {

        if (comic != null)
            comic.setValue(comics);

    }

    public void addComics(ArrayList<ComicModel> _comics) {

        if (_comics != null)
            comics.setValue(_comics);
    }


}
