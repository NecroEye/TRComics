package com.muratcangzm.trcomics.views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.models.ComicModel;
import com.muratcangzm.trcomics.utils.FetchingWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChipRecycler extends RecyclerView.Adapter<ChipRecycler.ChipHolder> {


    private final String[] genres;
    private final Context context;
    private final CardViewAdapter cardViewAdapter;


    public ChipRecycler(final String[] genres, Context context, CardViewAdapter cardViewAdapter) {

        this.genres = genres;
        this.context = context;
        this.cardViewAdapter = cardViewAdapter;

    }

    @NonNull
    @Override
    public ChipHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.chip_layout, null);

        return new ChipHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChipHolder holder, int position) {

        holder.chipName.setText(genres[position]);

        holder.chipName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // FIXME: 9.11.2023
                Log.d("Deneme", "onCheckedChanged: " + buttonView.getText().toString());
                filteredList(buttonView.getText().toString());
            }
        });


    }

    private void filteredList(String newText) {

        List<String> checkedGenres = null;
        ArrayList<ComicModel> filteredList = new ArrayList<>();
        for (ComicModel comicModel : FetchingWorker.comicModel) {
            checkedGenres = comicModel.getGenres().stream().filter(genre ->
                            genre.equals(newText))
                    .collect(Collectors.toList());
        }

        for(ComicModel comicModel : FetchingWorker.comicModel){
           for(String genre : checkedGenres){
               if(comicModel.getGenres().equals(genre)){
                   Log.d("Türler", "filteredList: " + genre + " ve " + comicModel.getGenres().get(0));
                   filteredList.add(comicModel);
               }
           }

        }
        if (filteredList.isEmpty()) {
            //not found
        } else {

            cardViewAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    public int getItemCount() {
        return genres.length;
    }


    public class ChipHolder extends RecyclerView.ViewHolder {

        private final Chip chipName;
        private final ChipGroup chipGroup;

        public ChipHolder(@NonNull View itemView) {
            super(itemView);

            chipGroup = itemView.findViewById(R.id.chipGroup);
            chipName = itemView.findViewById(R.id.chipName);

        }
    }
}
