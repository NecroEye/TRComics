package com.muratcangzm.trcomics.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.ActivityReadingBinding;
import com.muratcangzm.trcomics.recyclerView.CardViewModel;
import com.muratcangzm.trcomics.recyclerView.ReadingViewAdapter;

import java.util.ArrayList;

public class ReadingActivity extends AppCompatActivity {

    private ActivityReadingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent getData = getIntent();
        int[] images = getData.getIntArrayExtra("images");


        binding.recyclerReading.setAdapter(new ReadingViewAdapter(this, images));
        binding.recyclerReading.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerReading.setHasFixedSize(true);


    }



}