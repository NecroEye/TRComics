package com.muratcangzm.trcomics.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.DetailsFragmentLayoutBinding;
import com.muratcangzm.trcomics.models.ComicModel;
import com.muratcangzm.trcomics.saved_favorites.ComicDao;
import com.muratcangzm.trcomics.views.adapters.EpisodeAdapter;
import com.muratcangzm.trcomics.views.adapters.GenreAdapter;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetailsFragment extends Fragment {


    private DetailsFragmentLayoutBinding binding;
    private ComicModel models, favModel;
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




        preferences = requireActivity().getSharedPreferences("Favorites", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();




        binding.titleText.getBackground().setAlpha(120);
        binding.backButton.getBackground().setAlpha(120);
        binding.downloadButton.getBackground().setAlpha(120);
        binding.description.setMovementMethod(new ScrollingMovementMethod());


        boolean isSaved = preferences.getBoolean(models.getTitle(), false);

        if (isSaved) {
            binding.addFav.setImageResource(R.drawable.pressed_star);
            isFav = true;
        } else {
            binding.addFav.setImageResource(R.drawable.favorite_icon);
            isFav = false;

        }


        binding.recyclerGenre.setAdapter(new GenreAdapter(requireContext(), models.getGenres()));
        binding.recyclerGenre.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerGenre.setLayoutManager(linearLayoutManager);

        //Inside adapter manage reading section direction
        binding.episodeRecycler.setAdapter(new EpisodeAdapter(requireContext(), models.getEpisodes()));
        binding.episodeRecycler.setHasFixedSize(true);

        Glide.with(binding.bannerImage)
                        .load(models.getCoverUrl())
                                .placeholder(R.drawable.not_found)
                                        .error(R.drawable.not_found)
                                                .into(binding.bannerImage);

        binding.titleText.setText(models.getTitle());
        binding.description.setText(models.getDescription());

       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        binding.dateTextView.setText( sdf.format(models.getDate()));
        binding.authorTextView.setText("Yazar: " + models.getAuthor());


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
                Toast.makeText(requireContext(), "Favorilere Eklendi", Toast.LENGTH_SHORT).show();

            } else {
                isFav = !isFav;

                editor.remove(models.getTitle()).apply();
                editor.remove(models.getAuthor()).apply();
                binding.addFav.setImageResource(R.drawable.favorite_icon);
                Toast.makeText(requireContext(), "Favorilere Kaldırıldı", Toast.LENGTH_SHORT).show();

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
