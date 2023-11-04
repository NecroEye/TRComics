package com.muratcangzm.trcomics.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    public static MenuItem login, register, logout, profile;
    public static MaterialCardView materialCardView;
    public static TextView bannerUserName, bannerVerification, bannerStatusText;
    public static ImageView bannerProfilePicture;
    private View nav_header;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference docRef;
    private FirebaseUser currentUser;

    /**
     * ⠀⠀⠀⠀⠀⠀    ⠀⠀⠀⠀⢤⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
     * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⡾⠿⢿⡀⠀⠀⠀⠀⣠⣶⣿⣷⠀⠀⠀⠀
     * ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣦⣴⣿⡋⠀⠀⠈⢳⡄⠀⢠⣾⣿    ⣿⡆⠀⠀⠀
     * ⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⠿⠛⠉⠉⠁⠀⠀⠀⠹⡄⣿⣿⣿⠀⠀⢹⡇⠀⠀⠀
     * ⠀⠀⠀⠀⠀⣠⣾⡿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⣰⣏⢻⣿⣿⡆⠀⠸⣿⠀⠀⠀
     * ⠀⠀⠀⢀⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣿⣆⠹⣿⣷⠀⢘⣿⠀⠀⠀
     * ⠀⠀⢀⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⠋⠉⠛⠂⠹⠿⣲⣿⣿⣧⠀⠀
     * ⠀⢠⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣿⣿⣿⣷⣾⣿⡇⢀⠀⣼⣿⣿⣿⣧⠀
     * ⠰⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⡘⢿⣿⣿⣿⠀
     * ⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⣷⡈⠿⢿⣿⡆
     * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠛⠁⢙⠛⣿⣿⣿⣿⡟⠀⡿⠀⠀⢀⣿⡇
     * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣶⣤⣉⣛⠻⠇⢠⣿⣾⣿⡄⢻⡇
     * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣦⣤⣾⣿⣿⣿⣿⣆⠁
     */

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

        nav_header = binding.navView.getHeaderView(0);

        /**
         View decorView = getWindow().getDecorView();
         int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
         decorView.setSystemUiVisibility(uiOptions);
         **/


        materialCardView = nav_header.findViewById(R.id.bannerProfileIcon);
        bannerUserName = nav_header.findViewById(R.id.bannerProfileName);
        bannerStatusText = nav_header.findViewById(R.id.bannerStatusText);
        bannerVerification = nav_header.findViewById(R.id.bannerProfileVerification);
        bannerProfilePicture = nav_header.findViewById(R.id.bannerProfilePicture);


        setSupportActionBar(binding.toolbar);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        firebaseFirestore = FirebaseFirestore.getInstance();

        if (currentUser != null) {
            docRef = firebaseFirestore.collection("users").document(currentUser.getEmail());

        }


        NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(binding.navView, host.getNavController());


        host.getNavController().addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController,
                                             @NonNull NavDestination navDestination, @Nullable Bundle bundle) {


                Log.d("Anahtar", "onDestinationChanged: " + navDestination.getId());

                switch (navDestination.getId()) {

                    case 2131296631 -> getSupportActionBar().setTitle("Anasayfa");

                    case 2131296635 -> getSupportActionBar().setTitle("Profile");

                    case 2131296629 -> getSupportActionBar().setTitle("Favoriler");

                    case 2131296633 -> {

                        getSupportActionBar().setTitle("Giriş Ekranı");
                        binding.navView.setCheckedItem(R.id.nav_login);

                    }
                    case 2131296636 -> {

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

                if (item.getItemId() == R.id.nav_discord) {


                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.gg/SKu8r2eq9X")));

                }
                if (item.getItemId() == R.id.nav_about) {

                    Toast.makeText(MainActivity.this, "Developed by Muratcan Gözüm", Toast.LENGTH_SHORT).show();

                }
                if (item.getItemId() == R.id.nav_logout) {


                    FirebaseAuth.getInstance().signOut();

                    Toast.makeText(MainActivity.this, "Çıkış Yapıldı", Toast.LENGTH_SHORT).show();

                    updateUIBasedOnAuthState();


                    binding.navView.getMenu().clear();
                    binding.navView.inflateMenu(R.menu.nav_menu);

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
        updateUIBasedOnAuthState();
    }


    private void updateUIBasedOnAuthState() {
        if (currentUser != null) {
            profile.setVisible(true);
            login.setVisible(false);
            register.setVisible(false);
            logout.setVisible(true);
            fetchUserDataFromFirestore();
        } else {
            binding.navView.getMenu().clear();
            binding.navView.inflateMenu(R.menu.nav_menu);
            updateUIForLoggedOutState();
        }
    }


    private void updateUIForLoggedOutState() {
        profile.setVisible(false);
        login.setVisible(true);
        register.setVisible(true);
        logout.setVisible(false);

        materialCardView.setVisibility(View.INVISIBLE);
        bannerUserName.setVisibility(View.INVISIBLE);
        bannerStatusText.setVisibility(View.INVISIBLE);
        bannerVerification.setVisibility(View.INVISIBLE);
    }


    private void fetchUserDataFromFirestore() {
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        String username = document.getString("username");
                        String ImageUrl = document.getString("profilePicUrl");
                        if (ImageUrl != null && !ImageUrl.equals("boş"))
                            Glide
                                    .with(binding.getRoot())
                                    .load(ImageUrl)
                                    .error(R.drawable.not_found)
                                    .placeholder(R.drawable.not_found)
                                    .into(bannerProfilePicture);

                        bannerUserName.setText(username);
                    }
                } else {
                    Log.d("Firestore Error", task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
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
