package com.muratcangzm.trcomics.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.MainFragmentLayoutBinding;
import com.muratcangzm.trcomics.recyclerView.CardViewAdapter;
import com.muratcangzm.trcomics.recyclerView.CardViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainFragmentLayoutBinding binding;
    private ArrayList<SlideModel> slideModels;
    private CardViewAdapter cardViewAdapter;
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
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_four, 1,
                "Manga Name4", null,null,null,null));
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_four, 1,
                "Manga Name4", null,null,null,null));
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_one, 1,
                "Manga Name1", null,null,null,null));
        cardViewModels.add(new CardViewModel(2, R.drawable.cover_two, 1,
                "Manga Name2", null,null,null,null));

        cardViewAdapter = new CardViewAdapter(requireContext(), cardViewModels);

         binding.recyclerView.setAdapter(cardViewAdapter);
         binding.recyclerView.setHasFixedSize(true);
         binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),2));

         binding.searchView.clearFocus();
         binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 filteredList(newText);
                 return true;

             }
         });

        slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.cover_one, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.cover_two, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.cover_three, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.cover_four, ScaleTypes.CENTER_CROP));

        binding.imageSlider.setImageList(slideModels, ScaleTypes.CENTER_INSIDE);



    }

    private void filteredList(String newText) {

         ArrayList<CardViewModel> filteredList = new ArrayList<>();
         for(CardViewModel cardViewModel : cardViewModels){

             if(cardViewModel.getTitle().toLowerCase().contains(newText.toLowerCase())){

                 filteredList.add(cardViewModel);
             }
         }

         if(filteredList.isEmpty()){
             Toast.makeText(requireContext(), "Sonuç bulunamadı", Toast.LENGTH_SHORT).show();
         }
         else{

             cardViewAdapter.setFilteredList(filteredList);
         }

    }


}
