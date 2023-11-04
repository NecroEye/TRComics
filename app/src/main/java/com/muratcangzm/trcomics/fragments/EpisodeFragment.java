package com.muratcangzm.trcomics.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muratcangzm.trcomics.databinding.EpisodeFragmentLayoutBinding;
import com.muratcangzm.trcomics.models.ComicModel;
import com.muratcangzm.trcomics.views.adapters.EpisodeAdapter;

import java.util.ArrayList;

public class EpisodeFragment extends Fragment {


    private EpisodeFragmentLayoutBinding binding;
    private final ComicModel models;

    public EpisodeFragment(ComicModel _comicModels){

        this.models = _comicModels;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = EpisodeFragmentLayoutBinding.inflate(inflater, container, false);

        binding.episodeRecycler.setAdapter(new EpisodeAdapter(requireContext(), models.getEpisodes()));
        binding.episodeRecycler.setHasFixedSize(true);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
