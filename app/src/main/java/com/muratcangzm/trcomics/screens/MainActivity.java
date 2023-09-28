package com.muratcangzm.trcomics.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.navigation.NavigationView;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);
        binding.navView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawLayout, binding.toolbar,
                R.string.open_nav, R.string.close_nav);

        binding.drawLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("Anasayfa");


        binding.navView.setCheckedItem(R.id.nav_home);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.nav_home) {

            NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
            navController.navigate(R.id.navigateToMain);
            getSupportActionBar().setTitle("Anasayfa");


        } else if (item.getItemId() == R.id.nav_fav) {
            NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
            navController.navigate(R.id.navigateToFavorite);
            getSupportActionBar().setTitle("Favoriler");

        } else if (item.getItemId() == R.id.nav_discord) {


            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.gg/SKu8r2eq9X")));

        } else {

            Toast.makeText(this, "Developed by Muratcan Gözüm", Toast.LENGTH_SHORT).show();

        }
        binding.drawLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.drawLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }
}