package com.jainshobhit.coldstar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.animation.content.Content;
import com.bumptech.glide.Glide;
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview;
import com.jackandphantom.carouselrecyclerview.view.ReflectionImageView;
import com.jainshobhit.coldstar.DataNode.SearchMultiDataNode;

import java.util.HashMap;
import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.myViewHolder> {

    Context context;
    FragmentManager fragmentManager;
//    List<SearchMultiDataNode.Results> dataList;
    List<HashMap<String,Object>> dataList;

    public CarouselAdapter(Context context,FragmentManager fragmentManager, List<HashMap<String,Object>> dataList) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_carousel_layout,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
//        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + dataList.get(position).getBackdrop_path()).into(holder.imageView);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+dataList.get(position).get("poster_path")).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataList.get(position).get("isMovie").equals(true)){
                    MoviesFragment moviesFragment = new MoviesFragment().newInstance((int) dataList.get(position).get("id"));
                    moviesFragment.show(fragmentManager, moviesFragment.getTag());
                }
                else {
                    SeriesFragment seriesFragment = new SeriesFragment().newInstance((int) dataList.get(position).get("id"));
                    seriesFragment.show(fragmentManager, seriesFragment.getTag());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        ReflectionImageView imageView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.reflection_image_home);
        }
    }
}
