package com.jainshobhit.coldstar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.myViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    ArrayList<HashMap<String,Object>> dataToLoad;
    Boolean isSearch;

    public SearchAdapter(Context context,FragmentManager fragmentManager, ArrayList<HashMap<String, Object>> dataToLoad, Boolean isSearch) {
        this.context = context;
        this.fragmentManager=fragmentManager;
        this.dataToLoad = dataToLoad;
        this.isSearch=isSearch;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if(isSearch)
            view = inflater.inflate(R.layout.single_search_layout,parent, false);
        else
            view = inflater.inflate(R.layout.single_search_similar_layout,parent, false);

        myViewHolder viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        String url = "https://image.tmdb.org/t/p/w185" +dataToLoad.get(position).get("poster_path");
        int id = (int) dataToLoad.get(position).get("id");
        boolean isMovie = (boolean) dataToLoad.get(position).get("isMovie");
        Glide.with(context).load(url).into(holder.searchImage);
        Log.d("heloo", "Yaha bhi aaya");
        holder.searchImage.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return dataToLoad.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView searchImage;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            searchImage = itemView.findViewById(R.id.search_image_one);
        }
    }
}
