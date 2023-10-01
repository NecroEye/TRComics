package com.muratcangzm.trcomics.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);

        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(binding.navView, host.getNavController());



        host.getNavController().addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController,
                                             @NonNull NavDestination navDestination, @Nullable Bundle bundle) {



                switch (navDestination.getId()){

                    case 2131296590 ->
                            getSupportActionBar().setTitle("Anasayfa");

                    case 2131296589 ->
                            getSupportActionBar().setTitle("Favoriler");

                    case 2131296592 ->{

                        getSupportActionBar().setTitle("Giriş Ekranı");
                        binding.navView.setCheckedItem(R.id.nav_login);

                    }
                    case 2131296593 ->{

                        getSupportActionBar().setTitle("Kayıt Ekranı");
                        binding.navView.setCheckedItem(R.id.nav_register);
                    }

                }

                Log.d("Deneme", "onDestinationChanged: " + navDestination.getId());

            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawLayout, binding.toolbar,
                R.string.open_nav, R.string.close_nav);

        binding.drawLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("Anasayfa");



        binding.navView.setCheckedItem(R.id.nav_home);


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