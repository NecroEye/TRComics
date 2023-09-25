package com.muratcangzm.trcomics.screens;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.ActivityDetailsBinding;
import com.muratcangzm.trcomics.recyclerView.EpisodeAdapter;
import com.muratcangzm.trcomics.recyclerView.GenreAdapter;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private String title;
    private String description;
    private boolean isFav = false; // temporary variable

    private Integer image;
    private String author;
    private final String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int REQUEST_CODE = 100;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.titleText.getBackground().setAlpha(120);
        binding.backButton.getBackground().setAlpha(120);
        binding.downloadButton.getBackground().setAlpha(120);
        binding.description.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        author = intent.getStringExtra("author");
        image = intent.getIntExtra("image", 0);
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


        binding.backButton.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.addFav.setOnClickListener(v -> {

            if (isFav == false) {
                isFav = !isFav;
                binding.addFav.setImageResource(R.drawable.pressed_star);
                Toast.makeText(this, "Favorilere Eklendi", Toast.LENGTH_SHORT).show();
            } else {
                isFav = !isFav;
                binding.addFav.setImageResource(R.drawable.favorite_icon);
                Toast.makeText(this, "Favorilere Kaldırıldı", Toast.LENGTH_SHORT).show();

            }

        });


        binding.downloadButton.setOnClickListener(v -> {

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                //permission granted, do work


            }
            else if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                Snackbar.make(this, binding.getRoot(), "İzin Gerekli", Snackbar.LENGTH_INDEFINITE)
                        .setAction("İzinleri Yönet", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent1 = new Intent();
                                intent1.setAction(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                                Uri uri = Uri.fromParts("package", DetailsActivity.this.getPackageName(),null);
                                intent1.setData(uri);
                                startActivity(intent1);
                            }
                        }).setActionTextColor(R.color.gold)
                        .show();

            }
            else{

                ActivityCompat.requestPermissions(this,permissions,REQUEST_CODE);
                //Permission Denied, request permission


            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            //Permission Granted

        }
        else{

            //Permission Denied

        }


    }



}