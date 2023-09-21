package com.muratcangzm.trcomics.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muratcangzm.trcomics.databinding.FavoriteFragmentBinding;

public class FavoriteFragment extends Fragment {

    private FavoriteFragmentBinding binding;

    public FavoriteFragment(){

        //Empty Constructor


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FavoriteFragmentBinding.inflate(getLayoutInflater(), container, false);


        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
