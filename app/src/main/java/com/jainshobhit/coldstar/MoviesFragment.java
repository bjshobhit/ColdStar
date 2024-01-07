package com.jainshobhit.coldstar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jainshobhit.coldstar.DataNode.MovieDetailsNode;
import com.jainshobhit.coldstar.DataNode.SearchMultiDataNode;
import com.jainshobhit.coldstar.DataNode.VideosNode;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesFragment extends BottomSheetDialogFragment {
    private static final String MOVIE_ID = "MOVIE_ID";

    public MoviesFragment() {
    }
    public static MoviesFragment newInstance(int id) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putInt(MOVIE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    private int movie_id;
    private ImageView back_image;
    private TextView movie_title,movie_time_details,movie_desc,movie_genre;
    private Button watchTrailer;
    RecyclerView similarMovies;
    SearchAdapter similarAdapter;
    ArrayList<HashMap<String,Object>> similarDataToLoad = new ArrayList<>();
    String trailerUrl = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        back_image = view.findViewById(R.id.single_movie_backImage);
        movie_title = view.findViewById(R.id.single_movie_title);
        movie_time_details = view.findViewById(R.id.single_movie_time);
        movie_desc = view.findViewById(R.id.single_movie_desc);
        movie_genre = view.findViewById(R.id.single_movie_genre);
        similarMovies = view.findViewById(R.id.movies_recview_similar);
        watchTrailer = view.findViewById(R.id.watch_movie_trailer);

        similarMovies.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        similarAdapter = new SearchAdapter(getActivity().getApplicationContext(), getActivity().getSupportFragmentManager(), similarDataToLoad,false);
        similarMovies.setAdapter(similarAdapter);

        movie_id = getArguments().getInt(MOVIE_ID);

        fillMovieData();
        fillSimilarData();
        loadTrailerButton();

        watchTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!trailerUrl.equals(null)){
                    Uri trailerUri = Uri.parse(trailerUrl);
                    startActivity(new Intent(Intent.ACTION_VIEW,trailerUri));
                }
            }
        });

        return view;
    }

    private void loadTrailerButton() {
        Call<VideosNode> call = ApiRef.getInstance().getData().getMoviesVideos(movie_id);
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

            }
        });
    }

    private void fillSimilarData() {
        similarDataToLoad.clear();
        Call<SearchMultiDataNode> similarMovie = ApiRef.getInstance().getData().getMovieSimilar(movie_id);

        similarMovie.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode myData = response.body();
//                similarRecView.setVisibility(View.VISIBLE);
//                similartext.setVisibility(View.VISIBLE);
                for(SearchMultiDataNode.Results results1 : myData.getResults()){
                    if(results1.getPoster_path()==null) continue;
                    HashMap<String,Object> oneResult = new HashMap<>();
                    oneResult.put("id",results1.getId());
                    oneResult.put("poster_path",results1.getPoster_path());
                    oneResult.put("isMovie",true);
                    similarDataToLoad.add(oneResult);
                    Log.d("heloo", "data aaya");
                }
                similarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {
                Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillMovieData() {
        Call<MovieDetailsNode> dataNode = ApiRef.getInstance().getData().getMovieDetailsById(movie_id);
        dataNode.enqueue(new Callback<MovieDetailsNode>() {
            @Override
            public void onResponse(Call<MovieDetailsNode> call, Response<MovieDetailsNode> response) {
                MovieDetailsNode details = response.body();
                movie_title.setText(details.getTitle());
                movie_desc.setText(details.getOverview());
                Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500" + details.getBackdrop_path()).into(back_image);
                String time_details = details.getRelease_date().substring(0,4) + " | " + details.getRuntime() + "min";
                movie_time_details.setText(time_details);
                StringBuilder genre_details = new StringBuilder();
                for(MovieDetailsNode.Genres genres : details.getGenres()){
                    genre_details.append(genres.getName());
                    genre_details.append(" | ");
                }
//                genre_details.substring(0,genre_details.length()-2);
                movie_genre.setText(genre_details.substring(0,genre_details.length()-2));


            }

            @Override
            public void onFailure(Call<MovieDetailsNode> call, Throwable t) {

            }
        });
    }
}