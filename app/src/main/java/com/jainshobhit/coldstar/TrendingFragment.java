package com.jainshobhit.coldstar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import me.ibrahimsn.lib.SmoothBottomBar;

public class TrendingFragment extends Fragment {
    TabLayout tabLayoutofTrending;
    TabItem movies,series;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trending, container, false);
        SmoothBottomBar bottomBar = getActivity().findViewById(R.id.mainBottomNav);
        bottomBar.setItemActiveIndex(2);

        tabLayoutofTrending = view.findViewById(R.id.tablayoutoftrending);
        movies = view.findViewById(R.id.trendingMovies);
        series = view.findViewById(R.id.trendingSeries);
        getChildFragmentManager().beginTransaction().replace(R.id.framelayoutoftrending,new TrendingMoviesFragment()).commit();


        tabLayoutofTrending.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    getChildFragmentManager().beginTransaction().replace(R.id.framelayoutoftrending,new TrendingMoviesFragment()).commit();
                }
                if (tab.getPosition()==1){
                    getChildFragmentManager().beginTransaction().replace(R.id.framelayoutoftrending,new TrendingShowsFragment()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }
}