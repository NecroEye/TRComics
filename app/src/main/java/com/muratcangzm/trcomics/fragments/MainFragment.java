package com.muratcangzm.trcomics.fragments;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.MainFragmentLayoutBinding;
import com.muratcangzm.trcomics.models.ComicModel;
import com.muratcangzm.trcomics.screens.DetailsActivity;
import com.muratcangzm.trcomics.utils.FetchingWorker;
import com.muratcangzm.trcomics.views.CardViewAdapter;
import com.muratcangzm.trcomics.views.CardViewModel;
import com.muratcangzm.trcomics.views.ChipRecycler;

import java.util.ArrayList;
import java.util.UUID;

public class MainFragment extends Fragment {

    private MainFragmentLayoutBinding binding;
    private ArrayList<SlideModel> slideModels;
    private CardViewAdapter cardViewAdapter;
    public static ArrayList<CardViewModel> cardViewModels;
    private ArrayList<ComicModel> testModel = new ArrayList<>();

    public MainFragment() {
        //Empty Constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = MainFragmentLayoutBinding.inflate(getLayoutInflater(), container, false);

        binding.shimmerLayout.startShimmerAnimation();
        binding.shimmerSlider.startShimmerAnimation();

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest
                .Builder(FetchingWorker.class)
                .build();

        WorkManager.getInstance(requireContext()).enqueue(workRequest);

        WorkManager.getInstance(requireContext())
                .getWorkInfoByIdLiveData(workRequest.getId())
                .observe(getViewLifecycleOwner(), new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {

                        if (workInfo != null && workInfo.getState().isFinished()) {
                            new Handler().postDelayed(new Runnable() {
                                @Override public void run() {
                                    binding.shimmerLayout.stopShimmerAnimation();
                                    binding.shimmerSlider.stopShimmerAnimation();
                                    binding.shimmerSlider.setVisibility(View.GONE);
                                    binding.shimmerLayout.setVisibility(View.GONE);
                                    binding.recyclerView.setVisibility(View.VISIBLE);
                                    binding.imageSlider.setVisibility(View.VISIBLE);
                                    testModel.addAll(FetchingWorker.comicModel);

                                    Log.d("Veri", "run: " + testModel.size());

                                }
                            }, 1500);
                        }

                    }
                });

        Log.d("Veri", "onCreateView: " + testModel.size());





        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        int[] i = new int[]{
                R.drawable.cover_one,
                R.drawable.cover_two,
                R.drawable.cover_three,
                R.drawable.cover_four};


        String[] genres = new String[]{
                "Horror",
                "Drama",
                "Action",
                "Thriller",
                "Comedy",
                "Romance",
                "Historical",
                "Isekai",
                "Fantasy",
                "Sci-fi",
                "Nsfw",
        };

        String[] episodes = new String[]{
                "Bölüm-7",
                "Bölüm-6",
                "Bölüm-5",
                "Bölüm-4",
                "Bölüm-3",
                "Bölüm-2",
                "Bölüm-1"
        };


        cardViewModels = new ArrayList<>();
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_one, 5,
                "Company and Private Life", "“What a dogshit life… honestly.” Though he had become a disciple of the Heavenly Demon amidst the strife-filled Demonic Sect, Yeon So-Woon gets betrayed by his disciple-brother and comes to meet his end. However… When he woke up, he’s back to the time when he was a kid, before he got dragged into the Demonic Sect?! Loath to repeat his hellish life in the Demonic Sect, Yeon So-Woon begins to plan for his new life. I’ll enact revenge on the Demonic Sect, Become an elder of the Murim Union, And thus, live a prosperous and respected life.", "muratcan gözüm", null, null, null, i, genres, episodes, null, false));
        cardViewModels.add(new CardViewModel(2, R.drawable.cover_two, 5,
                "Manga Name2", "açıklama", "Ahmet yesevi", null, null, null, i, genres, episodes, null, false));
        cardViewModels.add(new CardViewModel(3, R.drawable.cover_three, 1,
                "Manga Name3", null, null, null, null, null, null, null, null, null, false));
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_four, 1,
                "Manga Name4", null, null, null, null, null, null, null, null, null, false));
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_one, 1,
                "Manga Name5", null, null, null, null, null, null, null, null, null, false));
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_four, 1,
                "Manga Name4", null, null, null, null, null, null, null, null, null, false));
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_four, 1,
                "Manga Name4", null, null, null, null, null, null, null, null, null, false));
        cardViewModels.add(new CardViewModel(1, R.drawable.cover_one, 1,
                "Manga Name1", null, null, null, null, null, null, null, null, null, false));
        cardViewModels.add(new CardViewModel(2, R.drawable.cover_two, 1,
                "Manga Name2", null, null, null, null, null, null, null, null, null, false));

        cardViewAdapter = new CardViewAdapter(requireContext(), cardViewModels, requireActivity());

        binding.recyclerView.setAdapter(cardViewAdapter);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        binding.chipRecycler.setAdapter(new ChipRecycler(genres, requireContext()));
        binding.chipRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false));
        binding.chipRecycler.setHasFixedSize(true);


        binding.searchView.clearFocus();

        String videoPath = "android.resource://" + requireContext().getPackageName() + "/" + R.raw.starfall_banner;
        Uri path = Uri.parse(videoPath);
        binding.bannerAnimation.setVideoURI(path);
        binding.bannerAnimation.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                binding.bannerAnimation.start();
            }
        });
        binding.bannerAnimation.start();

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredList(newText);
                return true;

            }
        });


        slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.cover_one, "Mushoku Tensei - Isekai", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.cover_two, "When A Thousand Moons Rise", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.cover_three, "Return Of The Sss-Class Ranker", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.cover_four, "Global Power: I Can Control All The Elements", ScaleTypes.CENTER_CROP));


        binding.imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);


        binding.imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {

                Intent intent = new Intent(requireContext(), DetailsActivity.class);

                intent.putExtra("title", cardViewModels.get(i).getTitle());
                intent.putExtra("image", cardViewModels.get(i).getImage());
                intent.putExtra("author", cardViewModels.get(i).getAuthor());
                intent.putExtra("episodes", cardViewModels.get(i).getEpisodes());
                intent.putExtra("genres", cardViewModels.get(i).getGenres());
                intent.putExtra("images", cardViewModels.get(i).getImages());
                intent.putExtra("description", cardViewModels.get(i).getDescription());

                requireContext().startActivity(intent);
                requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }

            @Override
            public void doubleClick(int i) {

            }
        });


    }

    private void filteredList(String newText) {

        ArrayList<CardViewModel> filteredList = new ArrayList<>();
        for (CardViewModel cardViewModel : cardViewModels) {

            if (cardViewModel.getTitle().toLowerCase().contains(newText.toLowerCase())) {

                filteredList.add(cardViewModel);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "Sonuç bulunamadı", Toast.LENGTH_SHORT).show();
        } else {

            cardViewAdapter.setFilteredList(filteredList);
        }

    }


}
