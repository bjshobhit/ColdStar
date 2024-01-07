package com.jainshobhit.coldstar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeriesEpisodeAdapter extends RecyclerView.Adapter<SeriesEpisodeAdapter.myViewHolder> {

    Context context;
    List<HashMap<String,Object>> dataToLoad = new ArrayList<>();

    public SeriesEpisodeAdapter(Context context, List<HashMap<String, Object>> dataToLoad) {
        this.context = context;
        this.dataToLoad = dataToLoad;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_episode_layout,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        String url = "https://image.tmdb.org/t/p/w500" + dataToLoad.get(position).get("image");
        Glide.with(context).load(url).centerCrop().into(holder.episodeImage);
        holder.title.setText(dataToLoad.get(position).get("name").toString());
        holder.desc.setText(dataToLoad.get(position).get("details").toString());
    }

    @Override
    public int getItemCount() {
        return dataToLoad.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView episodeImage;
        TextView title,desc;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            episodeImage = itemView.findViewById(R.id.single_episode_image);
            title = itemView.findViewById(R.id.single_episode_name);
            desc = itemView.findViewById(R.id.single_episode_details);
        }
    }
}
