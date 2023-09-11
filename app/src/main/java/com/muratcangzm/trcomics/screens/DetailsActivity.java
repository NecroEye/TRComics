package com.muratcangzm.trcomics.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.ActivityDetailsBinding;
import com.muratcangzm.trcomics.recyclerView.GenreAdapter;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private String title;
    private Integer image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.titleText.getBackground().setAlpha(120);


        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        image = intent.getIntExtra("image",0);
        String[] episodes = intent.getStringArrayExtra("episodes");
        String[] genres = intent.getStringArrayExtra("genres");
        int[] images = intent.getIntArrayExtra("images");

        binding.recyclerGenre.setAdapter(new GenreAdapter(this, genres));
        binding.recyclerGenre.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerGenre.setLayoutManager(linearLayoutManager);


        binding.bannerImage.setImageResource(image);
        binding.titleText.setText(title);

        binding.goToReading.setOnClickListener(view ->{

            Intent readingSection = new Intent(this, ReadingActivity.class);
            readingSection.putExtra("images", images);
            startActivity(readingSection);
        });

    }





}