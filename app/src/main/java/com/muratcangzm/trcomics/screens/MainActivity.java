package com.muratcangzm.trcomics.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    public static MenuItem login, register, logout, profile;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Menu menu = binding.navView.getMenu();

        logout = menu.findItem(R.id.nav_logout);
        login = menu.findItem(R.id.nav_login);
        register = menu.findItem(R.id.nav_register);
        profile = menu.findItem(R.id.nav_profile);

        setSupportActionBar(binding.toolbar);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(binding.navView, host.getNavController());


        host.getNavController().addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController,
                                             @NonNull NavDestination navDestination, @Nullable Bundle bundle) {


                switch (navDestination.getId()){

                    case 2131296599 ->
                            getSupportActionBar().setTitle("Anasayfa");

                    case 2131296832 ->
                        getSupportActionBar().setTitle("Profile");

                    case 2131296598 ->
                            getSupportActionBar().setTitle("Favoriler");

                    case 2131296601 ->{

                        getSupportActionBar().setTitle("Giriş Ekranı");
                        binding.navView.setCheckedItem(R.id.nav_login);

                    }
                    case 2131296603 ->{

                        getSupportActionBar().setTitle("Kayıt Ekranı");
                        binding.navView.setCheckedItem(R.id.nav_register);
                    }

                }

            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawLayout, binding.toolbar,
                R.string.open_nav, R.string.close_nav);

        binding.drawLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("Anasayfa");




        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.nav_discord){


                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.gg/SKu8r2eq9X")));

                }
                if(item.getItemId() == R.id.nav_about){

                    Toast.makeText(MainActivity.this, "Developed by Muratcan Gözüm", Toast.LENGTH_SHORT).show();

                }
                if(item.getItemId() == R.id.nav_logout){



                    FirebaseAuth.getInstance().signOut();

                    Toast.makeText(MainActivity.this, "Çıkış Yapıldı", Toast.LENGTH_SHORT).show();

                    login.setVisible(true);
                    register.setVisible(true);
                    logout.setVisible(false);
                    profile.setVisible(false);

                    NavController controller = Navigation.findNavController(MainActivity.this, R.id.fragmentContainerView);
                    controller.navigate(R.id.logoutSuccess);

                }


                NavigationUI.onNavDestinationSelected(item, host.getNavController());


                binding.drawLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        binding.navView.setCheckedItem(R.id.nav_home);


    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(currentUser != null){

            profile.setVisible(true);
            login.setVisible(false);
            register.setVisible(false);
            logout.setVisible(true);

        }
        else{
            profile.setVisible(false);
            login.setVisible(true);
            register.setVisible(true);
            logout.setVisible(false);
        }
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