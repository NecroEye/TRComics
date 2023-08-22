package com.muratcangzm.trcomics.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muratcangzm.trcomics.R;

import java.util.ArrayList;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {


    private final Context context;
    private final ArrayList<CardViewModel> cardViewModels;

    public CardViewAdapter(Context context, ArrayList<CardViewModel> cardViewModels) {
        this.context = context;
        this.cardViewModels = cardViewModels;
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

    }

    @Override
    public int getItemCount() {
        return cardViewModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView banner;
        final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            banner = itemView.findViewById(R.id.cardView_image);
            title = itemView.findViewById(R.id.cardView_title);


        }
    }

}
