package com.jainshobhit.coldstar;

import com.jainshobhit.coldstar.DataNode.ImagesNode;
import com.jainshobhit.coldstar.DataNode.MovieDetailsNode;
import com.jainshobhit.coldstar.DataNode.SearchMultiDataNode;
import com.jainshobhit.coldstar.DataNode.SeasonsDetailsNode;
import com.jainshobhit.coldstar.DataNode.SeriesDetailsNode;
import com.jainshobhit.coldstar.DataNode.VideosNode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBapi {
    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/search/multi?include_adult=false&language=en-US&page=1")
    Call<SearchMultiDataNode> searchData(@Query("query") String name);


    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/movie/{movie_id}/similar?language=en-US&page=1")
    Call<SearchMultiDataNode> getMovieSimilar(@Path("movie_id") int movie_id);


    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/trending/movie/day?language=en-US")
    Call<SearchMultiDataNode> getTrendingMovies();


    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/movie/now_playing?language=en-US&page=1&region=IN")
    Call<SearchMultiDataNode> getNowPlayingMovies();

    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/movie/popular?language=en-US&page=1&region=IN")
    Call<SearchMultiDataNode> getPopularMovies();


    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/movie/top_rated?language=en-US&page=1&region=IN")
    Call<SearchMultiDataNode> getTopRatedMovies();

    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/trending/tv/day?language=en-US")
    Call<SearchMultiDataNode> getTrendingShows();

    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/tv/airing_today?language=en-US&page=1&timezone=IN")
    Call<SearchMultiDataNode> getAiringTodaySeries();

    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/tv/popular?language=en-US&page=1")
    Call<SearchMultiDataNode> getPopularSeries();

    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/tv/top_rated?language=en-US&page=1")
    Call<SearchMultiDataNode> getTopRatedSeries();


    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/trending/all/day?language=en-US")
    Call<SearchMultiDataNode> getTrending();


    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/{type}/{type_id}/images?include_image_language=en")
    Call<ImagesNode> getImages(@Path("type") String type, @Path("type_id") String type_id);


    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&watch_region=IN")
    Call<SearchMultiDataNode> getByGenre(@Query("with_genres") String genre);


    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/movie/{movie_id}?language=en-US")
    Call<MovieDetailsNode> getMovieDetailsById(@Path("movie_id") int movie_id);

    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    Call<SearchMultiDataNode> getLatestReleaseMovies(@Query("release_date.gte") String date);



    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/movie/upcoming?language=en-US&page=1&region=IN")
    Call<SearchMultiDataNode> getUpcommingMovies();



    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/tv/{series_id}?language=en-US")
    Call<SeriesDetailsNode> getSeriesData(@Path("series_id") int series_id);



    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/tv/{series_id}/season/{season_number}?language=en-US")
    Call<SeasonsDetailsNode> getSeasonsDetails(@Path("series_id") int series_id, @Path("season_number") int season_number);




    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/tv/{series_id}/similar?language=en-US&page=1")
    Call<SearchMultiDataNode> getSeriesSimilar(@Path("series_id") int series_id);




    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/movie/{movie_id}/videos?language=en-US")
    Call<VideosNode> getMoviesVideos(@Path("movie_id") int movie_id);




    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/tv/{series_id}/videos?language=en-US")
    Call<VideosNode> getSeriesVideos(@Path("series_id") int series_id);




    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjk4NzliMzUyNjk4YzRhM2MzMjhkODQzY2FiZjM4MSIsInN1YiI6IjY1NTdhMjY0MjI5MzFhMDEzOThkYzg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rbkbp-Y_v7sdnuTxWzwSQ8wxQcbapiCOhLDT38HFFYY",
            "accept: application/json"
    })
    @GET("3/trending/all/day?language=en-US")
    Call<SearchMultiDataNode> getAllTrending();




}
