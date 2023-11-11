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
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
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
    private ArrayList<ComicModel> allComicModels = new ArrayList<>();
    private final LifecycleOwner owner;

    public CardViewAdapter(Context context, LifecycleOwner owner, LiveData<ArrayList<ComicModel>> _comicModels) {
        this.context = context;
        this.owner = owner;

        _comicModels.observe(owner, new Observer<ArrayList<ComicModel>>() {
            @Override
            public void onChanged(ArrayList<ComicModel> comicModels) {

                allComicModels.addAll(comicModels);
                notifyDataSetChanged();

            }
        });


    }

    public void setFilteredList(ArrayList<ComicModel> filteredList) {

        this.allComicModels = filteredList;
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
                .load(allComicModels.get(position).getCoverUrl())
                .placeholder(R.drawable.not_found)
                .error(R.drawable.not_found)
                .into(holder.banner);
        holder.title.setText(allComicModels.get(position).getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                NavDirections action = MainFragmentDirections.mainToDetail(new ComicModel(
                        allComicModels.get(position).getAuthor(),
                        allComicModels.get(position).getCoverUrl(),
                        allComicModels.get(position).getDate(),
                        allComicModels.get(position).getDescription(),
                        allComicModels.get(position).getEpisodes(),
                        allComicModels.get(position).isFavorite(),
                        allComicModels.get(position).getGenres(),
                        allComicModels.get(position).getTitle()
                ));

                NavController navController = Navigation.findNavController(v);
                navController.navigate(action);


            }
        });

    }

    @Override
    public int getItemCount() {
        return  allComicModels == null ? 0 : allComicModels.size();
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
