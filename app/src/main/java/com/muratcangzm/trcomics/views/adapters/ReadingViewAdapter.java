package com.muratcangzm.trcomics.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.muratcangzm.trcomics.R;

public class ReadingViewAdapter extends RecyclerView.Adapter<ReadingViewAdapter.ViewHolder> {


    private final Context context;
    private int[] images;


    public ReadingViewAdapter(Context context, int... images){

        this.context = context;
        this.images = images;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.reading_recycler, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.image.setImageResource(images[position]);


    }


    @Override
    public int getItemCount() {
        return images.length;
    }


  public class ViewHolder extends RecyclerView.ViewHolder{


        final ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.recyclerImage);


        }
    }


}
