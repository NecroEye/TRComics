package com.muratcangzm.trcomics.views.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.fragments.MainFragmentDirections;
import com.muratcangzm.trcomics.models.ComicModel;

import java.util.ArrayList;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {


    private final Context context;
    private final Activity activity;
    private ArrayList<ComicModel> comicModels;

    public CardViewAdapter(Context context, ArrayList<ComicModel> _comicModels, Activity activity) {
        this.context = context;
        this.comicModels = _comicModels;
        this.activity = activity;
    }

    public void setFilteredList(ArrayList<ComicModel> filteredList) {

        this.comicModels = filteredList;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cardview_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(holder.banner)
                .load(comicModels.get(position).getCoverUrl())
                .placeholder(R.drawable.not_found)
                .error(R.drawable.not_found)
                .into(holder.banner);
        holder.title.setText(comicModels.get(position).getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                NavDirections action = MainFragmentDirections.mainToDetail(new ComicModel(
                        comicModels.get(position).getAuthor(),
                        comicModels.get(position).getCoverUrl(),
                        comicModels.get(position).getDate(),
                        comicModels.get(position).getDescription(),
                        comicModels.get(position).getEpisodes(),
                        comicModels.get(position).isFavorite(),
                        comicModels.get(position).getGenres(),
                        comicModels.get(position).getTitle()
                ));

                NavController navController = Navigation.findNavController(v);
                navController.navigate(action);


            }
        });

    }

    @Override
    public int getItemCount() {
        return comicModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView banner;
        final TextView title;
        final CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            banner = itemView.findViewById(R.id.cardView_image);
            title = itemView.findViewById(R.id.cardView_title);
            cardView = itemView.findViewById(R.id.cardView_container);

        }
    }

}
