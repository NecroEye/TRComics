package com.muratcangzm.trcomics.fragments;

import android.annotation.SuppressLint;
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
import com.muratcangzm.trcomics.recyclerView.FavoritesAdapter;

public class FavoriteFragment extends Fragment {

    private FavoriteFragmentBinding binding;
    private FavoritesAdapter favoritesAdapter;

    public FavoriteFragment(){

        //Empty Constructor


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FavoriteFragmentBinding.inflate(getLayoutInflater(), container, false);

        favoritesAdapter = new FavoritesAdapter(requireContext(), requireActivity(), MainFragment.cardViewModels);
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
