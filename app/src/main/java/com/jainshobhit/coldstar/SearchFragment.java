package com.jainshobhit.coldstar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.jainshobhit.coldstar.DataNode.SearchMultiDataNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.ibrahimsn.lib.SmoothBottomBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    TextInputEditText searchText;
    RecyclerView searchRecView;
    RecyclerView similarRecView;
    SearchAdapter searchAdapter;
    SearchAdapter similarAdapter;
    ShimmerFrameLayout shimmerFrameLayout;
    TextView viewmoreofsearchrec,similartext;
    ImageView sImage1,sImage2,sImage3,sImage4,sImage5,sImage6,sImage7,sImage8,sImage9;
    ArrayList<HashMap<String,Object>> dataToLoad = new ArrayList<>();
    ArrayList<HashMap<String,Object>> similarDataToLoad = new ArrayList<>();
    private List<SearchMultiDataNode.Results> results;
    LinearLayout searchLayout,suggestionLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        SmoothBottomBar bottomBar = getActivity().findViewById(R.id.mainBottomNav);
        bottomBar.setItemActiveIndex(1);
        searchText = view.findViewById(R.id.search_movies);
        searchRecView=view.findViewById(R.id.search_recview);
        searchLayout = view.findViewById(R.id.search_layout);
        suggestionLayout=view.findViewById(R.id.suggestion_layout);
        shimmerFrameLayout = view.findViewById(R.id.shimmerlayoutofsearch);
        viewmoreofsearchrec = view.findViewById(R.id.viewmoreofsearchrecycler);
        viewmoreofsearchrec.setVisibility(View.GONE);
        similartext=view.findViewById(R.id.search_similar);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),3,RecyclerView.VERTICAL,false);
        searchRecView.setLayoutManager(gridLayoutManager);
        searchAdapter = new SearchAdapter(getActivity().getApplicationContext(),getActivity().getSupportFragmentManager(),dataToLoad,true);
        searchRecView.setAdapter(searchAdapter);
        similarRecView=view.findViewById(R.id.search_recview_similar);
        similarRecView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),1,RecyclerView.HORIZONTAL,false));
        similarAdapter = new SearchAdapter(getActivity().getApplicationContext(), getActivity().getSupportFragmentManager(), similarDataToLoad,false);
        similarRecView.setAdapter(similarAdapter);


        sImage1 = view.findViewById(R.id.search_image1);
        sImage2 = view.findViewById(R.id.search_image2);
        sImage3 = view.findViewById(R.id.search_image3);
        sImage4 = view.findViewById(R.id.search_image4);
        sImage5 = view.findViewById(R.id.search_image5);
        sImage6 = view.findViewById(R.id.search_image6);
        sImage7 = view.findViewById(R.id.search_image7);
        sImage8 = view.findViewById(R.id.search_image8);
        sImage9 = view.findViewById(R.id.search_image9);

        loadSuggestionImage();

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                String search = textView.getText().toString();
                shimmerFrameLayout.setVisibility(View.VISIBLE);
                shimmerFrameLayout.startShimmer();
                viewmoreofsearchrec.setVisibility(View.GONE);
                searchLayout.setVisibility(View.VISIBLE);
                suggestionLayout.setVisibility(View.GONE);
                searchMovie(search);


                return true;
            }
        });
        viewmoreofsearchrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=6;i<results.size();i++){
                    if(results.get(i).getPoster_path()==null) continue;
                    HashMap<String,Object> oneResult = new HashMap<>();
                    oneResult.put("id",results.get(i).getId());
                    oneResult.put("poster_path",results.get(i).getPoster_path());
                    if (results.get(i).getTitle()!=null)
                        oneResult.put("isMovie",true);
                    else
                        oneResult.put("isMovie",false);
                    dataToLoad.add(oneResult);
                }
                searchAdapter.notifyDataSetChanged();
                viewmoreofsearchrec.setVisibility(View.GONE);
            }
        });
        
        



        return view;
    }

    private void loadSuggestionImage() {
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getAllTrending();
        call.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode dataNode = response.body();
                int i=0;
                for (SearchMultiDataNode.Results movData: dataNode.getResults()){
                    if (i==9) break;
                    if(i==0){
                        Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500"+movData.getPoster_path()).into(sImage1);
                        sImage1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (movData.getTitle()!=null){
                                    MoviesFragment moviesFragment = new MoviesFragment().newInstance(movData.getId());
                                    moviesFragment.show(getActivity().getSupportFragmentManager(), moviesFragment.getTag());
                                }
                                else{
                                    SeriesFragment seriesFragment = new SeriesFragment().newInstance(movData.getId());
                                    seriesFragment.show(getActivity().getSupportFragmentManager(), seriesFragment.getTag());
                                }
                            }
                        });
                    } else if(i==1){
                        Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500"+movData.getPoster_path()).into(sImage2);
                        sImage2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (movData.getTitle()!=null){
                                    MoviesFragment moviesFragment = new MoviesFragment().newInstance(movData.getId());
                                    moviesFragment.show(getActivity().getSupportFragmentManager(), moviesFragment.getTag());
                                }
                                else{
                                    SeriesFragment seriesFragment = new SeriesFragment().newInstance(movData.getId());
                                    seriesFragment.show(getActivity().getSupportFragmentManager(), seriesFragment.getTag());
                                }
                            }
                        });
                    } else if (i == 2) {
                        Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500"+movData.getPoster_path()).into(sImage3);
                        sImage3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (movData.getTitle()!=null){
                                    MoviesFragment moviesFragment = new MoviesFragment().newInstance(movData.getId());
                                    moviesFragment.show(getActivity().getSupportFragmentManager(), moviesFragment.getTag());
                                }
                                else{
                                    SeriesFragment seriesFragment = new SeriesFragment().newInstance(movData.getId());
                                    seriesFragment.show(getActivity().getSupportFragmentManager(), seriesFragment.getTag());
                                }
                            }
                        });
                    } else if(i==3){
                        Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500"+movData.getPoster_path()).into(sImage4);
                        sImage4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (movData.getTitle()!=null){
                                    MoviesFragment moviesFragment = new MoviesFragment().newInstance(movData.getId());
                                    moviesFragment.show(getActivity().getSupportFragmentManager(), moviesFragment.getTag());
                                }
                                else{
                                    SeriesFragment seriesFragment = new SeriesFragment().newInstance(movData.getId());
                                    seriesFragment.show(getActivity().getSupportFragmentManager(), seriesFragment.getTag());
                                }
                            }
                        });
                    } else if(i==4){
                        Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500"+movData.getPoster_path()).into(sImage5);
                        sImage5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (movData.getTitle()!=null){
                                    MoviesFragment moviesFragment = new MoviesFragment().newInstance(movData.getId());
                                    moviesFragment.show(getActivity().getSupportFragmentManager(), moviesFragment.getTag());
                                }
                                else{
                                    SeriesFragment seriesFragment = new SeriesFragment().newInstance(movData.getId());
                                    seriesFragment.show(getActivity().getSupportFragmentManager(), seriesFragment.getTag());
                                }
                            }
                        });
                    } else if (i == 5) {
                        Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500"+movData.getPoster_path()).into(sImage6);
                        sImage6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (movData.getTitle()!=null){
                                    MoviesFragment moviesFragment = new MoviesFragment().newInstance(movData.getId());
                                    moviesFragment.show(getActivity().getSupportFragmentManager(), moviesFragment.getTag());
                                }
                                else{
                                    SeriesFragment seriesFragment = new SeriesFragment().newInstance(movData.getId());
                                    seriesFragment.show(getActivity().getSupportFragmentManager(), seriesFragment.getTag());
                                }
                            }
                        });
                    } else if(i==6){
                        Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500"+movData.getPoster_path()).into(sImage7);
                        sImage7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (movData.getTitle()!=null){
                                    MoviesFragment moviesFragment = new MoviesFragment().newInstance(movData.getId());
                                    moviesFragment.show(getActivity().getSupportFragmentManager(), moviesFragment.getTag());
                                }
                                else{
                                    SeriesFragment seriesFragment = new SeriesFragment().newInstance(movData.getId());
                                    seriesFragment.show(getActivity().getSupportFragmentManager(), seriesFragment.getTag());
                                }
                            }
                        });
                    } else if(i==7){
                        Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500"+movData.getPoster_path()).into(sImage8);
                        sImage8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (movData.getTitle()!=null){
                                    MoviesFragment moviesFragment = new MoviesFragment().newInstance(movData.getId());
                                    moviesFragment.show(getActivity().getSupportFragmentManager(), moviesFragment.getTag());
                                }
                                else{
                                    SeriesFragment seriesFragment = new SeriesFragment().newInstance(movData.getId());
                                    seriesFragment.show(getActivity().getSupportFragmentManager(), seriesFragment.getTag());
                                }
                            }
                        });
                    } else if (i == 8) {
                        Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500"+movData.getPoster_path()).into(sImage9);
                        sImage9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (movData.getTitle()!=null){
                                    MoviesFragment moviesFragment = new MoviesFragment().newInstance(movData.getId());
                                    moviesFragment.show(getActivity().getSupportFragmentManager(), moviesFragment.getTag());
                                }
                                else{
                                    SeriesFragment seriesFragment = new SeriesFragment().newInstance(movData.getId());
                                    seriesFragment.show(getActivity().getSupportFragmentManager(), seriesFragment.getTag());
                                }
                            }
                        });
                    }
                    i++;
                }
            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {

            }
        });
    }

    private void searchMovie(String search) {
        dataToLoad.clear();
        Call<SearchMultiDataNode> searchData = ApiRef.getInstance().getData().searchData(search);


        searchData.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode data = response.body();
                searchRecView.setVisibility(View.VISIBLE);
                int i = 0;
                for(SearchMultiDataNode.Results movData : data.getResults()){
                    if(i==6) break;
                    if(movData.getPoster_path()==null) continue;
                    HashMap<String,Object> oneResult = new HashMap<>();
                    oneResult.put("id",movData.getId());
                    oneResult.put("poster_path",movData.getPoster_path());
                    if (movData.getTitle()!=null)
                        oneResult.put("isMovie",true);
                    else
                        oneResult.put("isMovie",false);
                    dataToLoad.add(oneResult);

                    i++;
                }
                results = data.getResults();
                searchAdapter.notifyDataSetChanged();
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                viewmoreofsearchrec.setClickable(true);
                if (data.getResults().size()>6){
                    viewmoreofsearchrec.setText("View More");
                    viewmoreofsearchrec.setVisibility(View.VISIBLE);
                }
                similartext.setVisibility(View.VISIBLE);
                if(results.size()>0)
                    searchSimilar(results.get(0).getId());
                else {
                    viewmoreofsearchrec.setVisibility(View.VISIBLE);
                    viewmoreofsearchrec.setText("No Data Found !!");
                    viewmoreofsearchrec.setClickable(false);
                    searchRecView.setVisibility(View.GONE);
                    similarRecView.setVisibility(View.GONE);
                    similartext.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                viewmoreofsearchrec.setVisibility(View.VISIBLE);
                viewmoreofsearchrec.setText("Check Your Internet Connection and Try Again!!");
                viewmoreofsearchrec.setClickable(false);
                searchRecView.setVisibility(View.GONE);
                similarRecView.setVisibility(View.GONE);
                similartext.setVisibility(View.GONE);
                Toast.makeText(getActivity().getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void searchSimilar(Integer id) {
        similarDataToLoad.clear();
        if(dataToLoad.get(0).get("isMovie").equals(true)){
            searchSimilarMovie(id);
        }
        else {
            searchSimilarSeries(id);
        }



    }

    private void searchSimilarSeries(Integer id) {
        Call<SearchMultiDataNode> similarMovie = ApiRef.getInstance().getData().getSeriesSimilar(id);

        similarMovie.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode myData = response.body();
                similarRecView.setVisibility(View.VISIBLE);
                similartext.setVisibility(View.VISIBLE);
                for(SearchMultiDataNode.Results results1 : myData.getResults()){
                    if(results1.getPoster_path()==null) continue;
                    HashMap<String,Object> oneResult = new HashMap<>();
                    oneResult.put("id",results1.getId());
                    oneResult.put("poster_path",results1.getPoster_path());
                    oneResult.put("isMovie",false);
                    similarDataToLoad.add(oneResult);
                }
                similarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {
                Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchSimilarMovie(Integer id) {
        Call<SearchMultiDataNode> similarMovie = ApiRef.getInstance().getData().getMovieSimilar(id);

        similarMovie.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode myData = response.body();
                similarRecView.setVisibility(View.VISIBLE);
                similartext.setVisibility(View.VISIBLE);
                for(SearchMultiDataNode.Results results1 : myData.getResults()){
                    if(results1.getPoster_path()==null) continue;
                    HashMap<String,Object> oneResult = new HashMap<>();
                    oneResult.put("id",results1.getId());
                    oneResult.put("poster_path",results1.getPoster_path());
                    oneResult.put("isMovie",true);
                    similarDataToLoad.add(oneResult);
                }
                similarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {
                Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}