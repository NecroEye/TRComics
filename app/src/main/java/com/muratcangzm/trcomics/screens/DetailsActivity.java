package com.muratcangzm.trcomics.screens;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.ActivityDetailsBinding;
import com.muratcangzm.trcomics.fragments.MainFragment;
import com.muratcangzm.trcomics.recyclerView.CardViewModel;
import com.muratcangzm.trcomics.recyclerView.EpisodeAdapter;
import com.muratcangzm.trcomics.recyclerView.GenreAdapter;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private boolean isFav = false;

    private final String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int REQUEST_CODE = 100;
    private SharedPreferences preferences;
    private ArrayList<CardViewModel> cardViewModel;

    @SuppressLint("ResourceAsColor")
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

        if (isSaved){
            binding.addFav.setImageResource(R.drawable.pressed_star);
            Log.d("Deneme", "onStart Saved ");
            isFav = true;
        }
        else{
            binding.addFav.setImageResource(R.drawable.favorite_icon);
            isFav = false;
            Log.d("Deneme", "onStart can't saved ");

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
                binding.addFav.setImageResource(R.drawable.pressed_star);
                Toast.makeText(this, "Favorilere Eklendi", Toast.LENGTH_SHORT).show();
            } else {
                isFav = !isFav;
                editor.remove(cardViewModel.get(position).getTitle()).apply();

                binding.addFav.setImageResource(R.drawable.favorite_icon);
                Toast.makeText(this, "Favorilere Kaldırıldı", Toast.LENGTH_SHORT).show();

            }

        });


        binding.downloadButton.setOnClickListener(v -> {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                //permission granted, do work


            } else if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Snackbar.make(this, binding.getRoot(), "İzin Gerekli", Snackbar.LENGTH_INDEFINITE)
                        .setAction("İzinleri Yönet", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent settings = new Intent();
                                settings.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", DetailsActivity.this.getPackageName(), null);
                                settings.setData(uri);
                                startActivity(settings);
                            }
                        }).setActionTextColor(R.color.gold)
                        .show();

            } else {

                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
                //Permission Denied, request permission


            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            //Permission Granted

        } else {

            //Permission Denied

        }


    }


}