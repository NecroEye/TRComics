package com.muratcangzm.trcomics.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.muratcangzm.trcomics.R;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeHolder> {


    private final Context context;
    private final ArrayList<String> episodes;


    public EpisodeAdapter(Context context, ArrayList<String> episodes){

        this.context = context;
        this.episodes = episodes;

    }


    @NonNull
    @Override
    public EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.epsiode_recycler, parent, false);

        return new EpisodeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeHolder holder, int position) {

        holder.episodeText.setText(episodes.get(position));

        holder.episodeText.setOnClickListener(v ->{


            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.detailToReading);

            holder.seen.setVisibility(View.VISIBLE);
        });

    }



    @Override
    public int getItemCount() {
        return episodes.size();
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
