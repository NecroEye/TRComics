package com.muratcangzm.trcomics.fragments;

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
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.databinding.MainFragmentLayoutBinding;
import com.muratcangzm.trcomics.models.ComicModel;
import com.muratcangzm.trcomics.utils.FetchingWorker;
import com.muratcangzm.trcomics.views.MainActivity;
import com.muratcangzm.trcomics.views.adapters.CardViewAdapter;
import com.muratcangzm.trcomics.views.adapters.ChipRecycler;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    private MainFragmentLayoutBinding binding;
    private ArrayList<SlideModel> slideModels = new ArrayList<>();
    private CardViewAdapter cardViewAdapter;
    private ArrayList<ComicModel> realModel = new ArrayList<>();

    public MainFragment() {
        //Empty Constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = MainFragmentLayoutBinding.inflate(getLayoutInflater(), container, false);

        binding.shimmerLayout.startShimmerAnimation();
        binding.shimmerSlider.startShimmerAnimation();

        if(getActivity() instanceof MainActivity && !((MainActivity)getActivity()).getSupportActionBar().isShowing())
            ((MainActivity) getActivity()).getSupportActionBar().show();

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
                                @Override
                                public void run() {

                                    binding.shimmerLayout.stopShimmerAnimation();
                                    binding.shimmerSlider.stopShimmerAnimation();
                                    binding.shimmerSlider.setVisibility(View.GONE);
                                    binding.shimmerLayout.setVisibility(View.GONE);
                                    binding.recyclerView.setVisibility(View.VISIBLE);
                                    binding.imageSlider.setVisibility(View.VISIBLE);

                                    realModel.clear();
                                    realModel.addAll(FetchingWorker.comicModel);

                                    if (realModel.size() >= 5) {
                                        slideModels.clear();
                                        slideModels.add(new SlideModel(realModel.get(5).getCoverUrl(), realModel.get(5).getTitle(), ScaleTypes.CENTER_CROP));
                                        slideModels.add(new SlideModel(realModel.get(4).getCoverUrl(), realModel.get(4).getTitle(), ScaleTypes.CENTER_CROP));
                                        slideModels.add(new SlideModel(realModel.get(3).getCoverUrl(), realModel.get(3).getTitle(), ScaleTypes.CENTER_CROP));
                                    }
                                    else{
                                        slideModels.add(new SlideModel(R.drawable.not_found, "Başlık yok", ScaleTypes.CENTER_CROP));
                                    }


                                    binding.imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);

                                    binding.imageSlider.setItemClickListener(new ItemClickListener() {
                                        @Override
                                        public void onItemSelected(int i) {


                                            for (ComicModel comicModel : realModel) {

                                                if (slideModels.get(i).getTitle().equals(comicModel.getTitle())) {

                                                    NavDirections action = MainFragmentDirections.mainToDetail(comicModel);

                                                    NavController navController = Navigation.findNavController(binding.imageSlider);
                                                    navController.navigate(action);
                                                }
                                            }

                                        }


                                        @Override
                                        public void doubleClick(int i) {

                                        }
                                    });


                                    Log.d("Veri", "run: " + realModel.size());

                                }
                            }, 575);
                        }

                    }
                });


        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        String[] genres = new String[]{
                "Korku",
                "Dram",
                "Aksiyon",
                "Gerilim",
                "Komedi",
                "Romantik",
                "Fantastik",
                "Bilim-Kurgu",
                "Nsfw",
        };

        cardViewAdapter = new CardViewAdapter(requireContext(), realModel, requireActivity());

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


    }

    private void filteredList(String newText) {

        ArrayList<ComicModel> filteredList = new ArrayList<>();
        for (ComicModel comicModel : realModel) {

            if (comicModel.getTitle().toLowerCase().contains(newText.toLowerCase())) {

                filteredList.add(comicModel);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "Sonuç bulunamadı", Toast.LENGTH_SHORT).show();
        } else {

            cardViewAdapter.setFilteredList(filteredList);
        }

    }


}
