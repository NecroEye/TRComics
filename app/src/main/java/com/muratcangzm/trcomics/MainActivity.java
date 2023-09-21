package com.muratcangzm.trcomics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.muratcangzm.trcomics.databinding.ActivityMainBinding;
import com.muratcangzm.trcomics.fragments.FavoriteFragment;
import com.muratcangzm.trcomics.fragments.MainFragment;

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

      if(savedInstanceState == null){

          getSupportFragmentManager()
                  .beginTransaction()
                  .add(R.id.fragment_container, new MainFragment(), null)
                  .commit();
      }

        binding.navView.setCheckedItem(R.id.nav_home);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.nav_home) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new MainFragment(), null)
                    .commit();

        } else if (item.getItemId() == R.id.nav_fav) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new FavoriteFragment(), null)
                    .commit();
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