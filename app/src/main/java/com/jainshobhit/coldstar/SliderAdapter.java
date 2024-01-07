package com.jainshobhit.coldstar;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {
    private Context context;
    private FragmentManager fragmentManager;
    private List<SliderItem> mSliderItems = new ArrayList<>();

    public SliderAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    public void renewItems(List<SliderItem> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(SliderItem sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SliderItem sliderItem = mSliderItems.get(position);

        if(sliderItem.getTitleImage() != null){
            Glide.with(viewHolder.itemView)
                    .load("https://image.tmdb.org/t/p/w500"+sliderItem.getTitleImage())
                    .fitCenter()
                    .into(viewHolder.imageGifContainer);
        }
        else{
            viewHolder.textViewDescription.setText(sliderItem.getTitle());
            viewHolder.textViewDescription.setTextSize(25);
            viewHolder.textViewDescription.setTextColor(Color.WHITE);
        }
        Glide.with(viewHolder.itemView)
                .load("https://image.tmdb.org/t/p/w500"+sliderItem.getBackImage())
                .fitCenter()
                .into(viewHolder.imageViewBackground);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
                if(sliderItem.isMovie){
                    MoviesFragment moviesFragment = new MoviesFragment().newInstance(sliderItem.id);
                    moviesFragment.show(fragmentManager, moviesFragment.getTag());
                }
                else {
                    SeriesFragment seriesFragment = new SeriesFragment().newInstance(sliderItem.id);
                    seriesFragment.show(fragmentManager, seriesFragment.getTag());
                }
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
