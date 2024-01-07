package com.jainshobhit.coldstar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.jainshobhit.coldstar.DataNode.SearchMultiDataNode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TrendingShowsFragment extends Fragment {

    ShimmerFrameLayout trendingShimmer;
    LinearLayout trendingData;

    RecyclerView trendingShowsRec,airingTodayRec,popularRec,topRatedRec;
    TextView viewAlltrendingShows,viewAllairingToday,viewAllpopular,viewAlltopRated;
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
        View view = inflater.inflate(R.layout.fragment_trending_shows, container, false);

        trendingShowsRec = view.findViewById(R.id.trendingShowsRecycler);
        airingTodayRec = view.findViewById(R.id.airingTodayShowsRecycler);
        popularRec = view.findViewById(R.id.popularShowsRecycler);
        topRatedRec = view.findViewById(R.id.topratedShowsRecycler);

        viewAlltrendingShows = view.findViewById(R.id.view_all_trending_series);
        viewAllpopular = view.findViewById(R.id.view_all_popular_series);
        viewAllairingToday = view.findViewById(R.id.view_all_airingtoday_series);
        viewAlltopRated = view.findViewById(R.id.view_all_toprated_series);

        trendingShimmer = view.findViewById(R.id.trending_series_shimmer);
        trendingData = view.findViewById(R.id.trending_series_data);

        trendingShowsRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        trendingAdapter = new TrendingAdapter(getContext(), getActivity().getSupportFragmentManager(),trendingListof3,false);
        trendingShowsRec.setAdapter(trendingAdapter);

        airingTodayRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        nowPlayingAdapter = new TrendingAdapter(getContext(), getActivity().getSupportFragmentManager(),nowPlayingListof3,false);
        airingTodayRec.setAdapter(nowPlayingAdapter);

        popularRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        popularAdapter = new TrendingAdapter(getContext(), getActivity().getSupportFragmentManager(),popularListof3,false);
        popularRec.setAdapter(popularAdapter);

        topRatedRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        topRatedAdapter = new TrendingAdapter(getContext(), getActivity().getSupportFragmentManager(),topratedListof3,false);
        topRatedRec.setAdapter(topRatedAdapter);

        viewAlltrendingShows.setVisibility(View.INVISIBLE);
        viewAlltopRated.setVisibility(View.INVISIBLE);
        viewAllpopular.setVisibility(View.INVISIBLE);
        viewAllairingToday.setVisibility(View.INVISIBLE);

        trendingData.setVisibility(View.INVISIBLE);
        trendingShimmer.setVisibility(View.VISIBLE);
        trendingShimmer.startShimmer();
        topRatedAdapter.setOnItemInflatedListener(new TrendingAdapter.OnItemInflatedListener() {
            @Override
            public void onItemInflated(int position) {
                if (position == topRatedAdapter.getItemCount() -1){
                    trendingShimmer.stopShimmer();
                    trendingShimmer.setVisibility(View.GONE);
                    trendingData.setVisibility(View.VISIBLE);

                    viewAlltrendingShows.setVisibility(View.VISIBLE);
                    viewAlltopRated.setVisibility(View.VISIBLE);
                    viewAllpopular.setVisibility(View.VISIBLE);
                    viewAllairingToday.setVisibility(View.VISIBLE);

                }
            }
        });

        getTrendingData();
        getTopAiringData();
        getPopularData();
        getTopRatedData();

        viewAlltrendingShows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrendingViewAll trendingViewAll = TrendingViewAll.newInstance(false, (ArrayList<SearchMultiDataNode.Results>) trendingList,"Trending Shows");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,trendingViewAll).addToBackStack(null).commit();
            }
        });


        viewAllpopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrendingViewAll trendingViewAll = TrendingViewAll.newInstance(false, (ArrayList<SearchMultiDataNode.Results>) popularList,"Popular Shows");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,trendingViewAll).addToBackStack(null).commit();
            }
        });


        viewAllairingToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrendingViewAll trendingViewAll = TrendingViewAll.newInstance(false, (ArrayList<SearchMultiDataNode.Results>) nowPlayingList,"Airing Today Shows");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,trendingViewAll).addToBackStack(null).commit();
            }
        });


        viewAlltopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrendingViewAll trendingViewAll = TrendingViewAll.newInstance(false, (ArrayList<SearchMultiDataNode.Results>) topratedList,"Top Rated Shows");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,trendingViewAll).addToBackStack(null).commit();
            }
        });

        return view;
    }
    private static void getTopRatedData() {
        topratedList.clear();
        topratedListof3.clear();
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getTopRatedSeries();
        call.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode dataNode = response.body();
                for (SearchMultiDataNode.Results results : dataNode.getResults()){
                    topratedList.add(results);
                    topRatedAdapter.notifyDataSetChanged();
                }

                for(int i=0;i<3;i++){
                    topratedListof3.add(topratedList.get(i));
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
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getPopularSeries();
        call.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode dataNode = response.body();
                for (SearchMultiDataNode.Results results : dataNode.getResults()){
                    popularList.add(results);
                    popularAdapter.notifyDataSetChanged();
                }

                for(int i=0;i<3;i++){
                    popularListof3.add(popularList.get(i));
                }

            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {

            }
        });
    }

    private static void getTopAiringData() {
        nowPlayingList.clear();
        nowPlayingListof3.clear();
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getAiringTodaySeries();
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
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getTrendingShows();
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