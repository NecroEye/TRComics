package com.muratcangzm.trcomics.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Toast;

import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.ActivityDetailsBinding;
import com.muratcangzm.trcomics.recyclerView.EpisodeAdapter;
import com.muratcangzm.trcomics.recyclerView.GenreAdapter;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private String title;
    private String description;
    private  boolean isFav = false; // temporary variable

    private Integer image;
    private String author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.titleText.getBackground().setAlpha(120);
        binding.backButton.getBackground().setAlpha(120);
        binding.description.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        author = intent.getStringExtra("author");
        image = intent.getIntExtra("image",0);
        String[] episodes = intent.getStringArrayExtra("episodes");
        String[] genres = intent.getStringArrayExtra("genres");
        int[] images = intent.getIntArrayExtra("images");



        binding.recyclerGenre.setAdapter(new GenreAdapter(this, genres));
        binding.recyclerGenre.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerGenre.setLayoutManager(linearLayoutManager);

        Intent readingSection = new Intent(this, ReadingActivity.class);
        binding.episodeRecycler.setAdapter(new EpisodeAdapter(this, episodes, images, readingSection));
        binding.episodeRecycler.setHasFixedSize(true);

        binding.bannerImage.setImageResource(image);
        binding.titleText.setText(title);
        binding.description.setText(description);
        binding.dateTextView.setText("20/03/2017");
        binding.authorTextView.setText("Yazar: " + author);


        binding.backButton.setOnClickListener(v ->{
            onBackPressed();
        });

        binding.addFav.setOnClickListener(v ->{

            if(isFav == false){
                isFav = !isFav;
                binding.addFav.setImageResource(R.drawable.pressed_star);
                Toast.makeText(this, "Favorilere Eklendi", Toast.LENGTH_SHORT).show();
            }
            else{
                isFav = !isFav;
                binding.addFav.setImageResource(R.drawable.favorite_icon);
                Toast.makeText(this, "Favorilere Kaldırıldı", Toast.LENGTH_SHORT).show();

            }

        });

    }





}