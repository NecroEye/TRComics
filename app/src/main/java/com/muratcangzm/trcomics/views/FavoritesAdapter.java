package com.muratcangzm.trcomics.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.screens.DetailsActivity;
import java.util.ArrayList;


public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteHolder> {


    private final Context context;
    private final Activity activity;
    public ArrayList<CardViewModel> savedModel;




    public FavoritesAdapter(Context context, Activity activity, ArrayList<CardViewModel> savedModel){

        this.context = context;
        this.activity = activity;
        this.savedModel = savedModel;

    }




    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cardview_layout, parent,false);

        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.savedBanner.setImageResource(savedModel.get(position).getImage());
        holder.savedTitle.setText(savedModel.get(position).getTitle());

        holder.savedCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailsActivity.class);


                intent.putExtra("episodes", savedModel.get(position).getEpisodes());
                intent.putExtra("genres", savedModel.get(position).getGenres());
                intent.putExtra("images", savedModel.get(position).getImages());
                intent.putExtra("position", position);


                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


            }
        });

        holder.savedCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                // FIXME: 27.09.2023 custom alert dialog and remove the favs from sharedPreference.


                return false;
            }
        });

    }


    @Override
    public int getItemCount() {
        return savedModel.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder{

        final ImageView savedBanner;
        final TextView savedTitle;
        final CardView savedCardView;

        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);

            savedBanner = itemView.findViewById(R.id.cardView_image);
            savedTitle = itemView.findViewById(R.id.cardView_title);
            savedCardView = itemView.findViewById(R.id.cardView_container);

        }
    }

}
