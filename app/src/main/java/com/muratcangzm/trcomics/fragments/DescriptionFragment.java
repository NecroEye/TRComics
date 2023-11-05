package com.muratcangzm.trcomics.fragments;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.muratcangzm.trcomics.databinding.DescriptionFragmentLayoutBinding;
import com.muratcangzm.trcomics.models.ComicModel;
import com.muratcangzm.trcomics.viewmodels.ComicViewModel;
import com.muratcangzm.trcomics.views.adapters.GenreAdapter;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class DescriptionFragment extends Fragment {


    private DescriptionFragmentLayoutBinding binding;
    private final ComicModel model;
    private ComicViewModel viewModel;

    public DescriptionFragment(ComicModel _comicModels) {

        this.model = _comicModels;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DescriptionFragmentLayoutBinding.inflate(inflater, container, false);


        viewModel = new ViewModelProvider(requireActivity()).get(ComicViewModel.class);
        viewModel.addComic(model);

        viewModel.getComicModel().observe(getViewLifecycleOwner(), new Observer<ComicModel>() {
            @Override
            public void onChanged(ComicModel model) {

                binding.recyclerGenre.setAdapter(new GenreAdapter(requireContext(), model.getGenres()));
                binding.recyclerGenre.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.recyclerGenre.setLayoutManager(linearLayoutManager);

                binding.description.setText(model.getDescription());
                binding.description.setMovementMethod(new ScrollingMovementMethod());

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                binding.dateTextView.setText(sdf.format(model.getDate()));
                binding.authorTextView.setText("Yazar: " + model.getAuthor());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
