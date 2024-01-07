package com.jainshobhit.coldstar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jainshobhit.coldstar.DataNode.SearchMultiDataNode;

import java.util.ArrayList;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.myViewHolder>{

    Context context;
    FragmentManager fragmentManager;
    List<SearchMultiDataNode.Results> dataNodeList;
    boolean isMovie;

    private OnItemInflatedListener onItemInflatedListener;

    public void setOnItemInflatedListener(OnItemInflatedListener listener) {
        this.onItemInflatedListener = listener;
    }

    public TrendingAdapter(Context context,FragmentManager fragmentManager, List<SearchMultiDataNode.Results> dataNodeList,boolean isMovie) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.dataNodeList = dataNodeList;
        this.isMovie=isMovie;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_trending_layout,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        String url = "https://image.tmdb.org/t/p/w185" +dataNodeList.get(position).getPoster_path();
        int id = dataNodeList.get(position).getId();
        Glide.with(context).load(url).into(holder.poster);
        if(dataNodeList.get(position).getTitle()!=null)
            holder.title.setText(dataNodeList.get(position).getTitle());
        else
            holder.title.setText(dataNodeList.get(position).getName());
        holder.desc.setText(dataNodeList.get(position).getOverview());
        String genre = "";
        if(dataNodeList.get(position).getRelease_date()!=null)
            genre = dataNodeList.get(position).getRelease_date().substring(0,4) + " • ";
        else if(dataNodeList.get(position).getFirst_air_date()!=null)
            try {

            genre = dataNodeList.get(position).getFirst_air_date().substring(0,4) + " • ";
            } catch (Exception e){}
        if(isMovie)
            for (int i = 0; i < dataNodeList.get(position).getGenre_ids().size(); i++) {
                genre = genre + GenreStore.getMovieGenre(dataNodeList.get(position).getGenre_ids().get(i)) + " • ";
            }
        else
            for (int i = 0; i < dataNodeList.get(position).getGenre_ids().size(); i++) {
                genre = genre + GenreStore.getSeriesGenre(dataNodeList.get(position).getGenre_ids().get(i)) + " • ";
            }
        genre = genre.substring(0,genre.length()-2);
        holder.genre.setText(genre);


        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isMovie){
                    MoviesFragment moviesFragment = new MoviesFragment().newInstance(id);
                    moviesFragment.show(fragmentManager, moviesFragment.getTag());
                }
                else {
                    SeriesFragment seriesFragment = new SeriesFragment().newInstance(id);
                    seriesFragment.show(fragmentManager, seriesFragment.getTag());
                }
            }
        });


        if (onItemInflatedListener != null) {
            onItemInflatedListener.onItemInflated(position);

        }
    }

    @Override
    public int getItemCount() {
        return dataNodeList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title,desc,genre;
        AppCompatButton details;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.imageViewofonetrendingmovies);
            title = itemView.findViewById(R.id.titleofonetrendingmovie);
            desc = itemView.findViewById(R.id.descofonetrendingmovie);
            genre = itemView.findViewById(R.id.genreofonetrendingmovie);
            details = itemView.findViewById(R.id.detailsbtnofonetrendingmovie);
        }
    }

    public interface OnItemInflatedListener {
        void onItemInflated(int position);
    }
}
