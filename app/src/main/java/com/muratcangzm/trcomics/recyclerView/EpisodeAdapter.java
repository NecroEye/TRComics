package com.muratcangzm.trcomics.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muratcangzm.trcomics.R;
import com.muratcangzm.trcomics.screens.ReadingActivity;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeHolder> {


    private final Context context;
    private final String[] episodes;
    private final int[] images;
    private final Intent readingSection;


    public EpisodeAdapter(Context context, String[] episodes, int[] images, Intent intent){


        this.context = context;
        this.episodes = episodes;
        this.images = images;
        this.readingSection = intent;

    }


    @NonNull
    @Override
    public EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(context).inflate(R.layout.epsiode_recycler, parent, false);


        return new EpisodeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeHolder holder, int position) {

        holder.episodeText.setText(episodes[position]);

        holder.episodeText.setOnClickListener(v ->{

            readingSection.putExtra("images", images);
            holder.seen.setVisibility(View.VISIBLE);
            context.startActivity(readingSection);

        });

    }



    @Override
    public int getItemCount() {
        return episodes.length;
    }



    public class EpisodeHolder extends RecyclerView.ViewHolder{

        private final TextView episodeText;
        private final ImageView seen;

        public EpisodeHolder(@NonNull View itemView) {
            super(itemView);

            episodeText = itemView.findViewById(R.id.episode_textView);
            this.seen = itemView.findViewById(R.id.seen_icon);

        }
    }

}
