package com.muratcangzm.trcomics.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.ReadingFragmentLayoutBinding;
import com.muratcangzm.trcomics.views.adapters.ReadingViewAdapter;

public class ReadingFragment extends Fragment {


    private ReadingFragmentLayoutBinding binding;

    public ReadingFragment(){

        //Empty Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = ReadingFragmentLayoutBinding.inflate(inflater, container, false);



        binding.recyclerReading.setAdapter(new ReadingViewAdapter(requireContext(), new int[]{R.drawable.cover_one, R.drawable.cover_two, R.drawable.cover_three}));
        binding.recyclerReading.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerReading.setHasFixedSize(true);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
