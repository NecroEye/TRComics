package com.muratcangzm.trcomics.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.muratcangzm.trcomics.databinding.FavoriteFragmentBinding;
import com.muratcangzm.trcomics.recyclerView.CardViewModel;
import com.muratcangzm.trcomics.recyclerView.FavoritesAdapter;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    private FavoriteFragmentBinding binding;
    private FavoritesAdapter favoritesAdapter;
    private ArrayList<CardViewModel> savedModel = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    public FavoriteFragment() {

        //Empty Constructor


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FavoriteFragmentBinding.inflate(getLayoutInflater(), container, false);
        sharedPreferences = requireContext().getSharedPreferences("Favorites", Context.MODE_PRIVATE);

        Log.d("Deneme", "shared: " + sharedPreferences.getAll().toString());

        for(CardViewModel cardViewModel : MainFragment.cardViewModels){

            if(!sharedPreferences.getString(cardViewModel.getAuthor(), "Empty").contains("Empty")){

                Log.d("Deneme", "içerik: " + cardViewModel.getAuthor());
                savedModel.add(cardViewModel);


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


        Log.d("Deneme", "onViewCreated: " + savedModel.size());



    }
}
