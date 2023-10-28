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
import com.muratcangzm.trcomics.utils.FetchingWorker;

import java.util.ArrayList;


public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteHolder> {


    private final Context context;
    private final Activity activity;
    public ArrayList<ComicModel> savedModel;




    public FavoritesAdapter(Context context, Activity activity, ArrayList<ComicModel> savedModel){

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



        Glide.with(holder.savedBanner)
                        .load(savedModel.get(position).getCoverUrl())
                                .placeholder(R.drawable.not_found)
                                        .error(R.drawable.not_found)
                                                .into(holder.savedBanner);

        holder.savedTitle.setText(savedModel.get(position).getTitle());

        holder.savedCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (ComicModel comicModel : FetchingWorker.comicModel) {

                    if (savedModel.get(position).getTitle().equals(comicModel.getTitle())) {

                        // FIXME: 29.10.2023
                        NavDirections action = MainFragmentDirections.mainToDetail(comicModel);

                        NavController navController = Navigation.findNavController(v);
                       // navController.navigate(action);
                    }
                }
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
