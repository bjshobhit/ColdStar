package com.jainshobhit.coldstar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.jainshobhit.coldstar.DataNode.SearchMultiDataNode;
import com.jainshobhit.coldstar.DataNode.SeasonsDetailsNode;
import com.jainshobhit.coldstar.DataNode.SeriesDetailsNode;
import com.jainshobhit.coldstar.DataNode.VideosNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SeriesFragment extends BottomSheetDialogFragment {
    private static final String SERIES_ID = "SERIES_ID";

    public SeriesFragment() {
    }
    public static SeriesFragment newInstance(int id) {
        SeriesFragment fragment = new SeriesFragment();
        Bundle args = new Bundle();
        args.putInt(SERIES_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    int series_id;
    TabLayout seasonsTab;
    RecyclerView episodeRecycler,similarRec;
    SeriesEpisodeAdapter episodeAdapter;
    SearchAdapter similarAdapter;
    ImageView backImage;
    private Button watchTrailer;
    TextView title,seriesTime,description,genre;

    List<HashMap<String,Object>> episodedata = new ArrayList<>();
    ArrayList<HashMap<String,Object>> similarDataToLoad = new ArrayList<>();

    String trailerUrl = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series, container, false);

        seasonsTab = view.findViewById(R.id.series_season_tab_layout);
        episodeRecycler = view.findViewById(R.id.series_episodes_recview);
        backImage = view.findViewById(R.id.single_series_backImage);
        title = view.findViewById(R.id.single_series_title);
        seriesTime = view.findViewById(R.id.single_series_time);
        description = view.findViewById(R.id.single_series_desc);
        genre = view.findViewById(R.id.single_series_genre);
        similarRec = view.findViewById(R.id.series_recview_similar);
        watchTrailer = view.findViewById(R.id.watch_series_trailer);


        episodeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        episodeAdapter = new SeriesEpisodeAdapter(getContext(),episodedata);
        episodeRecycler.setAdapter(episodeAdapter);

        similarRec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        similarAdapter = new SearchAdapter(getContext(), getActivity().getSupportFragmentManager(), similarDataToLoad,false);
        similarRec.setAdapter(similarAdapter);
        series_id=getArguments().getInt(SERIES_ID);


        loadTabs();
        loadSimilar();
        loadTrailerButton();




        seasonsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int season = tab.getPosition()+1;
                episodedata.clear();
                episodeAdapter.notifyDataSetChanged();
                loadTabRecycler(season);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        watchTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trailerUrl != null){
                    Uri trailerUri = Uri.parse(trailerUrl);
                    startActivity(new Intent(Intent.ACTION_VIEW,trailerUri));
                }
            }
        });

        return view;
    }

    private void loadTrailerButton() {
        Call<VideosNode> call = ApiRef.getInstance().getData().getSeriesVideos(series_id);
        call.enqueue(new Callback<VideosNode>() {
            @Override
            public void onResponse(Call<VideosNode> call, Response<VideosNode> response) {
                VideosNode videosNode= response.body();
                for (VideosNode.Results results : videosNode.getResults()){
                    if(results.getType().equals("Trailer")){
                        if(results.getSite().equals("YouTube")){
                            watchTrailer.setText("Watch Trailer");
                            trailerUrl = "https://www.youtube.com/watch?v=" + results.getKey();
                            return;
                        }
                    }
                }
                for (VideosNode.Results results : videosNode.getResults()){
                    if(results.getType().equals("Teaser")){
                        if(results.getSite().equals("YouTube")){
                            watchTrailer.setText("Watch Teaser");
                            trailerUrl = "https://www.youtube.com/watch?v=" + results.getKey();
                            return;
                        }
                    }
                }

                watchTrailer.setText("Video Unavailable !");

            }

            @Override
            public void onFailure(Call<VideosNode> call, Throwable t) {
                watchTrailer.setText("Video Unavailable !");
            }
        });
    }

    private void loadSimilar() {
        similarDataToLoad.clear();
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getSeriesSimilar(series_id);
        call.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode detailsNode = response.body();
                for(SearchMultiDataNode.Results results1 : detailsNode.getResults()){
                    if(results1.getPoster_path()==null) continue;
                    HashMap<String,Object> oneResult = new HashMap<>();
                    oneResult.put("id",results1.getId());
                    oneResult.put("poster_path",results1.getPoster_path());
                    oneResult.put("isMovie",false);
                    similarDataToLoad.add(oneResult);
                    similarAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {

            }
        });
    }

    private void loadTabRecycler(int season) {
        episodedata.clear();
        Call<SeasonsDetailsNode> call = ApiRef.getInstance().getData().getSeasonsDetails(series_id,season);
        call.enqueue(new Callback<SeasonsDetailsNode>() {
            @Override
            public void onResponse(Call<SeasonsDetailsNode> call, Response<SeasonsDetailsNode> response) {
                SeasonsDetailsNode detailsNode = response.body();
                for (SeasonsDetailsNode.Episodes episodes : detailsNode.getEpisodes()){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("name",episodes.getName());
                    map.put("image",episodes.getStill_path());
                    String details = "S" + episodes.getSeason_number() + " E" + episodes.getEpisode_number()
                            + " • " + episodes.getAir_date() + " • " + episodes.getRuntime() + "m";
                    map.put("details",details);
                    episodedata.add(map);
                    episodeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SeasonsDetailsNode> call, Throwable t) {

            }
        });
    }

    private void loadTabs() {
        episodedata.clear();
        Call<SeriesDetailsNode> call = ApiRef.getInstance().getData().getSeriesData(series_id);
        call.enqueue(new Callback<SeriesDetailsNode>() {
            @Override
            public void onResponse(Call<SeriesDetailsNode> call, Response<SeriesDetailsNode> response) {
                SeriesDetailsNode seriesDetailsNode = response.body();
                title.setText(seriesDetailsNode.getName());
                description.setText(seriesDetailsNode.getOverview());
                try {
                    String timeStr = seriesDetailsNode.getFirst_air_date().substring(0,4)+ " - " + seriesDetailsNode.getLast_air_date().substring(0,4);
                    seriesTime.setText(timeStr);
                }catch (Exception e){seriesTime.setVisibility(View.GONE);}

                Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500"+seriesDetailsNode.getBackdrop_path()).into(backImage);

                StringBuilder genreStr = new StringBuilder();
                for(SeriesDetailsNode.Genres gen : seriesDetailsNode.getGenres()){
                    genreStr.append(gen.getName());
                    genreStr.append(" | ");
                }
                genre.setText(genreStr.substring(0,genreStr.length()-2));

                for(int i=1;i<=seriesDetailsNode.getNumber_of_seasons();i++){
                    TabLayout.Tab newTab= seasonsTab.newTab();
                    newTab.setText("Season " + i);
                    seasonsTab.addTab(newTab);
                }
            }

            @Override
            public void onFailure(Call<SeriesDetailsNode> call, Throwable t) {
            }
        });

//        for(int i=1;i<=15;i++){
//            TabLayout.Tab newTab= seasonsTab.newTab();
//            newTab.setText("Season " + i);
//            seasonsTab.addTab(newTab);
//        }


    }
}