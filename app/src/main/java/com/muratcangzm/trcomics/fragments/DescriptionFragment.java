package com.muratcangzm.trcomics.fragments;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.DescriptionFragmentLayoutBinding;
import com.muratcangzm.trcomics.models.ComicModel;
import com.muratcangzm.trcomics.views.adapters.EpisodeAdapter;
import com.muratcangzm.trcomics.views.adapters.GenreAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DescriptionFragment extends Fragment {


    private DescriptionFragmentLayoutBinding binding;
    private final ComicModel models;

    public DescriptionFragment(ComicModel _comicModels) {

        this.models = _comicModels;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DescriptionFragmentLayoutBinding.inflate(inflater, container, false);


        binding.recyclerGenre.setAdapter(new GenreAdapter(requireContext(), models.getGenres()));
        binding.recyclerGenre.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerGenre.setLayoutManager(linearLayoutManager);


        //Inside adapter manage reading section direction


        binding.description.setText(models.getDescription());
        binding.description.setMovementMethod(new ScrollingMovementMethod());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        binding.dateTextView.setText(sdf.format(models.getDate()));
        binding.authorTextView.setText("Yazar: " + models.getAuthor());


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
