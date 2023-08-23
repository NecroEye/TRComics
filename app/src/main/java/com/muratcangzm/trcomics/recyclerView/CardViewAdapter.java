package com.muratcangzm.trcomics.recyclerView;

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
import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {


    private final Context context;
    private ArrayList<CardViewModel> cardViewModels;

    public CardViewAdapter(Context context, ArrayList<CardViewModel> cardViewModels) {
        this.context = context;
        this.cardViewModels = cardViewModels;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.banner.setImageResource(cardViewModels.get(position).getImage());
        holder.title.setText(cardViewModels.get(position).getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);

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
