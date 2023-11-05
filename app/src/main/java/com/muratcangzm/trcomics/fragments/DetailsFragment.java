package com.muratcangzm.trcomics.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayoutMediator;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.DetailsFragmentLayoutBinding;
import com.muratcangzm.trcomics.fragments.fragmentAdapter.ViewPagerAdapter;
import com.muratcangzm.trcomics.models.ComicModel;
import com.muratcangzm.trcomics.saved_favorites.ComicDao;
import com.muratcangzm.trcomics.views.MainActivity;

public class DetailsFragment extends Fragment {


    private DetailsFragmentLayoutBinding binding;
    private ComicModel models;
    private boolean isFav = false;

    private SharedPreferences preferences;
    private ComicDao comicDao;

    public DetailsFragment() {

        //Empty Constructor

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DetailsFragmentLayoutBinding.inflate(inflater, container, false);

        models = DetailsFragmentArgs.fromBundle(getArguments()).getCurrentComic();

        if(getActivity() instanceof MainActivity)
            ((MainActivity) getActivity()).getSupportActionBar().hide();


        preferences = requireActivity().getSharedPreferences("Favorites", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        binding.titleText.getBackground().setAlpha(120);
        binding.backButton.getBackground().setAlpha(120);
        binding.downloadButton.getBackground().setAlpha(120);


        Glide.with(binding.bannerImage)
                .load(models.getCoverUrl())
                .placeholder(R.drawable.not_found)
                .error(R.drawable.not_found)
                .into(binding.bannerImage);

        binding.titleText.setText(models.getTitle());

        binding.viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),
                getLifecycle(),
                models));


        new TabLayoutMediator(binding.tableLayout, binding.viewPager,
                (tab, position) -> {

                    if (position == 0) tab.setText("Tanıtım");
                    else tab.setText("Bölümler");

                }).attach();


        boolean isSaved = preferences.getBoolean(models.getTitle(), false);

        if (isSaved) {
            binding.addFav.setImageResource(R.drawable.pressed_star);
            isFav = true;
        } else {
            binding.addFav.setImageResource(R.drawable.favorite_icon);
            isFav = false;

        }

        binding.backButton.setOnClickListener(v -> {

            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.detailToMain);

        });

        binding.addFav.setOnClickListener(v -> {

            if (isFav == false) {
                isFav = !isFav;

                editor.putBoolean(models.getTitle(), true).apply();
                editor.putString(models.getAuthor(), models.getAuthor()).apply();
                binding.addFav.setImageResource(R.drawable.pressed_star);

                Snackbar.make(requireContext(), v,"Favorilere Eklendi", Snackbar.LENGTH_SHORT)
                        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                        .show();

            } else {
                isFav = !isFav;

                editor.remove(models.getTitle()).apply();
                editor.remove(models.getAuthor()).apply();
                binding.addFav.setImageResource(R.drawable.favorite_icon);

                Snackbar.make(requireContext(), v,"Favorilerden Kaldırıldı", Snackbar.LENGTH_SHORT)
                        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                        .show();

            }

        });


        binding.downloadButton.setOnClickListener(v -> {


            // TODO: 24.10.2023 fix dagger hilt
            // ComicDatabase database = ComicModule.injectRoom(this);
            // comicDao = database.comicDao();

        });


        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
