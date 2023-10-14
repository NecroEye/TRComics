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

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {


    private final Context context;
    private final Activity activity;
    private ArrayList<CardViewModel> cardViewModels;

    public CardViewAdapter(Context context, ArrayList<CardViewModel> cardViewModels, Activity activity) {
        this.context = context;
        this.cardViewModels = cardViewModels;
        this.activity = activity;
    }

    public void setFilteredList(ArrayList<CardViewModel> filteredList){

        this.cardViewModels = filteredList;
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

        holder.banner.setImageResource(cardViewModels.get(position).getImage());
        holder.title.setText(cardViewModels.get(position).getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailsActivity.class);


                intent.putExtra("episodes", cardViewModels.get(position).getEpisodes());
                intent.putExtra("genres", cardViewModels.get(position).getGenres());
                intent.putExtra("images", cardViewModels.get(position).getImages());
                intent.putExtra("position", position);


                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


            }
        });

    }

    @Override
    public int getItemCount() {
        return cardViewModels.size();
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
