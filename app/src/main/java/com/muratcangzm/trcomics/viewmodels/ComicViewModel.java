package com.muratcangzm.trcomics.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.muratcangzm.trcomics.models.ComicModel;

import javax.inject.Inject;


public class ComicViewModel extends ViewModel {


    private MutableLiveData<ComicModel> comic = new MutableLiveData<>();

    public LiveData<ComicModel> getComicModel(){

        return comic;
    }

    public void addComic(ComicModel comics){

        if(comic != null)
            comic.setValue(comics);

    }


}
