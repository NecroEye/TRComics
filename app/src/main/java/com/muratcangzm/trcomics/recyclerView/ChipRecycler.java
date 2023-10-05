package com.muratcangzm.trcomics.recyclerView;

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

import java.util.ArrayList;

public class ChipRecycler extends RecyclerView.Adapter<ChipRecycler.ChipHolder> {


    private final String[] genres;
    private final Context context;


    public ChipRecycler(final String[] genres, Context context) {

        this.genres = genres;
        this.context = context;

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

                Log.d("Deneme", "onCheckedChanged: " + buttonView.getText().toString());

            }
        });


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
