package com.jainshobhit.coldstar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.jainshobhit.coldstar.DataNode.SearchMultiDataNode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingMoviesFragment extends Fragment {

    ShimmerFrameLayout trendingShimmer;
    LinearLayout trendingData;
    TextView viewAllTrending, viewAllNowPlaying, viewAllPopular,viewAllTopRated;
    RecyclerView trendingMoviesRec,nowPlayingRec,popularRec,topRatedRec;
    static TrendingAdapter trendingAdapter,nowPlayingAdapter,popularAdapter,topRatedAdapter;
    static List<SearchMultiDataNode.Results> trendingList = new ArrayList<>();
    static List<SearchMultiDataNode.Results> trendingListof3 = new ArrayList<>();
    static List<SearchMultiDataNode.Results> nowPlayingList = new ArrayList<>();
    static List<SearchMultiDataNode.Results> nowPlayingListof3 = new ArrayList<>();
    static List<SearchMultiDataNode.Results> popularList = new ArrayList<>();
    static List<SearchMultiDataNode.Results> popularListof3 = new ArrayList<>();
    static List<SearchMultiDataNode.Results> topratedList = new ArrayList<>();
    static List<SearchMultiDataNode.Results> topratedListof3 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trending_movies, container, false);

        trendingMoviesRec = view.findViewById(R.id.trendingMoviesRecycler);
        nowPlayingRec = view.findViewById(R.id.nowplayingMoviesRecycler);
        popularRec = view.findViewById(R.id.popularMoviesRecycler);
        topRatedRec = view.findViewById(R.id.topratedMoviesRecycler);

        viewAllTrending = view.findViewById(R.id.view_all_trending_movies);
        viewAllNowPlaying = view.findViewById(R.id.view_all_nowplaying_movies);
        viewAllPopular = view.findViewById(R.id.view_all_popular_movies);
        viewAllTopRated = view.findViewById(R.id.view_all_toprated_movies);

        trendingShimmer = view.findViewById(R.id.trending_movies_shimmer);
        trendingData = view.findViewById(R.id.trending_movies_data);
        trendingMoviesRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        nowPlayingAdapter = new TrendingAdapter(getContext(), getActivity().getSupportFragmentManager(), trendingListof3,true);
        trendingMoviesRec.setAdapter(nowPlayingAdapter);

        nowPlayingRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        trendingAdapter = new TrendingAdapter(getContext(), getActivity().getSupportFragmentManager(),nowPlayingListof3,true);
        nowPlayingRec.setAdapter(trendingAdapter);

        popularRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        popularAdapter = new TrendingAdapter(getContext(), getActivity().getSupportFragmentManager(),popularListof3,true);
        popularRec.setAdapter(popularAdapter);

        topRatedRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        topRatedAdapter = new TrendingAdapter(getContext(), getActivity().getSupportFragmentManager(),topratedListof3,true);
        topRatedRec.setAdapter(topRatedAdapter);

        trendingData.setVisibility(View.INVISIBLE);
        trendingShimmer.setVisibility(View.VISIBLE);
        trendingShimmer.startShimmer();

        viewAllTrending.setVisibility(View.INVISIBLE);
        viewAllNowPlaying.setVisibility(View.INVISIBLE);
        viewAllPopular.setVisibility(View.INVISIBLE);
        viewAllTopRated.setVisibility(View.INVISIBLE);

        topRatedAdapter.setOnItemInflatedListener(new TrendingAdapter.OnItemInflatedListener() {
            @Override
            public void onItemInflated(int position) {
                if (position == topRatedAdapter.getItemCount() -1){
                    trendingShimmer.stopShimmer();
                    trendingShimmer.setVisibility(View.GONE);
                    trendingData.setVisibility(View.VISIBLE);

                    viewAllTrending.setVisibility(View.VISIBLE);
                    viewAllNowPlaying.setVisibility(View.VISIBLE);
                    viewAllPopular.setVisibility(View.VISIBLE);
                    viewAllTopRated.setVisibility(View.VISIBLE);

                }
            }
        });

        getTrendingData();
        getNowPlayingData();
        getPopularData();
        getTopRatedData();


        viewAllTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrendingViewAll trendingViewAll = TrendingViewAll.newInstance(true, (ArrayList<SearchMultiDataNode.Results>) trendingList,"Trending Movies");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,trendingViewAll).addToBackStack(null).commit();
            }
        });



        viewAllPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrendingViewAll trendingViewAll = TrendingViewAll.newInstance(true, (ArrayList<SearchMultiDataNode.Results>) popularList,"Popular Movies");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,trendingViewAll).addToBackStack(null).commit();
            }
        });



        viewAllNowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrendingViewAll trendingViewAll = TrendingViewAll.newInstance(true, (ArrayList<SearchMultiDataNode.Results>) nowPlayingList,"Now Playing Movies");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,trendingViewAll).addToBackStack(null).commit();
            }
        });



        viewAllTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrendingViewAll trendingViewAll = TrendingViewAll.newInstance(true, (ArrayList<SearchMultiDataNode.Results>) topratedList,"Top Rated Movies");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,trendingViewAll).addToBackStack(null).commit();
            }
        });





        return view;
    }

    private static void getTopRatedData() {
        topratedList.clear();
        topratedListof3.clear();
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getTopRatedMovies();
        call.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode dataNode = response.body();
                for (SearchMultiDataNode.Results results : dataNode.getResults()){
                    topratedList.add(results);

                }

                for(int i=0;i<3;i++){
                    topratedListof3.add(topratedList.get(i));
                    topRatedAdapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {

            }
        });


    }

    private static void getPopularData() {
        popularList.clear();
        popularListof3.clear();
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getPopularMovies();
        call.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode dataNode = response.body();
                for (SearchMultiDataNode.Results results : dataNode.getResults()){
                    popularList.add(results);

                }

                for(int i=0;i<3;i++){
                    popularListof3.add(popularList.get(i));
                    popularAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {

            }
        });
    }

    private static void getNowPlayingData() {
        nowPlayingList.clear();
        nowPlayingListof3.clear();
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getNowPlayingMovies();
        call.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode dataNode = response.body();
                for (SearchMultiDataNode.Results results : dataNode.getResults()){
                    nowPlayingList.add(results);
                    nowPlayingAdapter.notifyDataSetChanged();
                }

                for(int i=0;i<3;i++){
                    nowPlayingListof3.add(nowPlayingList.get(i));
                }


            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {

            }
        });
    }

    private static void getTrendingData() {
        trendingList.clear();
        trendingListof3.clear();
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getTrendingMovies();
        call.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode dataNode = response.body();
                for (SearchMultiDataNode.Results results : dataNode.getResults()){
                    trendingList.add(results);
                    trendingAdapter.notifyDataSetChanged();
                }

                for(int i=0;i<3;i++){
                    trendingListof3.add(trendingList.get(i));

                }

            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {

            }
        });
    }
}