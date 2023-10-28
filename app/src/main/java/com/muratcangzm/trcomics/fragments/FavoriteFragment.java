package com.muratcangzm.trcomics.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.FavoriteFragmentBinding;
import com.muratcangzm.trcomics.models.ComicModel;
import com.muratcangzm.trcomics.utils.FetchingWorker;
import com.muratcangzm.trcomics.views.adapters.FavoritesAdapter;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    private FavoriteFragmentBinding binding;
    private FavoritesAdapter favoritesAdapter;
    private ArrayList<ComicModel> savedModel = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    public FavoriteFragment() {

        //Empty Constructor


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FavoriteFragmentBinding.inflate(getLayoutInflater(), container, false);
        sharedPreferences = requireContext().getSharedPreferences("Favorites", Context.MODE_PRIVATE);
        binding.favBackground.getBackground().setTint(requireContext().getColor(R.color.black));


        for(ComicModel comicModel : FetchingWorker.comicModel){

            if(!sharedPreferences.getString(comicModel.getAuthor(), "Empty").contains("Empty")){

                savedModel.add(comicModel);


            }
        }

        favoritesAdapter = new FavoritesAdapter(requireContext(), requireActivity(), savedModel);
        binding.favRecycler.setAdapter(favoritesAdapter);
        binding.favRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.favRecycler.setHasFixedSize(true);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }
}
