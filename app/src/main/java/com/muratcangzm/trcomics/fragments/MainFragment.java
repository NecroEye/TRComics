package com.muratcangzm.trcomics.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.MainFragmentLayoutBinding;
import com.muratcangzm.trcomics.recyclerView.CardViewAdapter;
import com.muratcangzm.trcomics.recyclerView.CardViewModel;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private MainFragmentLayoutBinding binding;
    private ArrayList<SlideModel> slideModels;
    private ArrayList<CardViewModel> cardViewModels;

     public MainFragment(){
        //Empty Constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = MainFragmentLayoutBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardViewModels = new ArrayList<>();
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_one, 1,
                "Manga Name1", null,null,null,null));
        cardViewModels.add(new CardViewModel(2, R.drawable.cover_two, 1,
                "Manga Name2", null,null,null,null));
        cardViewModels.add(new CardViewModel(3, R.drawable.cover_three, 1,
                "Manga Name3", null,null,null,null));
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_four, 1,
                "Manga Name4", null,null,null,null));
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_one, 1,
                "Manga Name5", null,null,null,null));

         binding.recyclerView.setAdapter(new CardViewAdapter(requireContext(), cardViewModels));
         binding.recyclerView.setHasFixedSize(true);
         binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),2));

        slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.cover_one, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.cover_two, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.cover_three, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.cover_four, ScaleTypes.CENTER_CROP));

        binding.imageSlider.setImageList(slideModels, ScaleTypes.CENTER_INSIDE);



    }





}
