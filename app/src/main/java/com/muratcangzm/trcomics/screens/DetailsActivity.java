package com.muratcangzm.trcomics.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.ActivityDetailsBinding;
import com.muratcangzm.trcomics.fragments.MainFragment;
import com.muratcangzm.trcomics.saved_favorites.ComicDao;
import com.muratcangzm.trcomics.views.CardViewModel;
import com.muratcangzm.trcomics.views.EpisodeAdapter;
import com.muratcangzm.trcomics.views.GenreAdapter;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private boolean isFav = false;

    private SharedPreferences preferences;
    private ComicDao comicDao;
    private ArrayList<CardViewModel> cardViewModel;

    @SuppressLint({"ResourceAsColor", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        preferences = getSharedPreferences("Favorites", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        if (MainFragment.cardViewModels != null) {

            cardViewModel = MainFragment.cardViewModels;

        } else {

            throw new NullPointerException("CardViewModel Array is Empty");

        }


        binding.titleText.getBackground().setAlpha(120);
        binding.backButton.getBackground().setAlpha(120);
        binding.downloadButton.getBackground().setAlpha(120);
        binding.description.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        String[] episodes = intent.getStringArrayExtra("episodes");
        String[] genres = intent.getStringArrayExtra("genres");
        int[] images = intent.getIntArrayExtra("images");

        int position = intent.getIntExtra("position", 0);


        boolean isSaved = preferences.getBoolean(cardViewModel.get(position).getTitle(), false);

        if (isSaved) {
            binding.addFav.setImageResource(R.drawable.pressed_star);
            isFav = true;
        } else {
            binding.addFav.setImageResource(R.drawable.favorite_icon);
            isFav = false;

        }


        binding.recyclerGenre.setAdapter(new GenreAdapter(this, genres));
        binding.recyclerGenre.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerGenre.setLayoutManager(linearLayoutManager);

        Intent readingSection = new Intent(this, ReadingActivity.class);
        binding.episodeRecycler.setAdapter(new EpisodeAdapter(this, episodes, images, readingSection));
        binding.episodeRecycler.setHasFixedSize(true);

        binding.bannerImage.setImageResource(cardViewModel.get(position).getImage());
        binding.titleText.setText(cardViewModel.get(position).getTitle());
        binding.description.setText(cardViewModel.get(position).getDescription());
        binding.dateTextView.setText("20/03/2017");
        binding.authorTextView.setText("Yazar: " + cardViewModel.get(position).getAuthor());


        binding.backButton.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.addFav.setOnClickListener(v -> {

            if (isFav == false) {
                isFav = !isFav;

                editor.putBoolean(cardViewModel.get(position).getTitle(), true).apply();
                editor.putString(cardViewModel.get(position).getAuthor(), cardViewModel.get(position).getAuthor()).apply();
                binding.addFav.setImageResource(R.drawable.pressed_star);
                Toast.makeText(this, "Favorilere Eklendi", Toast.LENGTH_SHORT).show();

            } else {
                isFav = !isFav;

                editor.remove(cardViewModel.get(position).getTitle()).apply();
                editor.remove(cardViewModel.get(position).getAuthor()).apply();
                binding.addFav.setImageResource(R.drawable.favorite_icon);
                Toast.makeText(this, "Favorilere Kaldırıldı", Toast.LENGTH_SHORT).show();

            }

        });


        binding.downloadButton.setOnClickListener(v -> {


            // TODO: 24.10.2023 fix dagger hilt
            // ComicDatabase database = ComicModule.injectRoom(this);
            // comicDao = database.comicDao();

        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}