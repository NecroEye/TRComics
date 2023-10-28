package com.muratcangzm.trcomics.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muratcangzm.trcomics.R;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreHolder> {


    private final ArrayList<String> genres;
    private final Context context;

    public GenreAdapter(Context context, ArrayList<String> genres) {

        this.genres = genres;
        this.context = context;

    }

    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.genre_layout, parent, false);
        return new GenreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreHolder holder, int position) {


        holder.genres.setText(genres.get(position));

    }


    @Override
    public int getItemCount() {
        return genres.size();
    }


    public class GenreHolder extends RecyclerView.ViewHolder {

        private final TextView genres;

        public GenreHolder(@NonNull View itemView) {
            super(itemView);

            genres = itemView.findViewById(R.id.genre_holder);

        }
    }

}
