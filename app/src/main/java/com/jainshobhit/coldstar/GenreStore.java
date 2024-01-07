package com.jainshobhit.coldstar;

import java.util.HashMap;
import java.util.Map;

public class GenreStore {
    private static final Map<Integer, String> movieGenresMap = new HashMap<>();
    private static final Map<Integer, String> seriesGenresMap = new HashMap<>();

    static {
        initializeGenres();
    }

    private static void initializeGenres() {
        movieGenresMap.put(28, "Action");
        movieGenresMap.put(12, "Adventure");
        movieGenresMap.put(16, "Animation");
        movieGenresMap.put(35, "Comedy");
        movieGenresMap.put(80, "Crime");
        movieGenresMap.put(99, "Documentary");
        movieGenresMap.put(18, "Drama");
        movieGenresMap.put(10751, "Family");
        movieGenresMap.put(14, "Fantasy");
        movieGenresMap.put(36, "History");
        movieGenresMap.put(27, "Horror");
        movieGenresMap.put(10402, "Music");
        movieGenresMap.put(9648, "Mystery");
        movieGenresMap.put(10749, "Romance");
        movieGenresMap.put(878, "Science Fiction");
        movieGenresMap.put(10770, "TV Movie");
        movieGenresMap.put(53, "Thriller");
        movieGenresMap.put(10752, "War");
        movieGenresMap.put(37, "Western");


        seriesGenresMap.put(10759, "Action & Adventure");
        seriesGenresMap.put(16, "Animation");
        seriesGenresMap.put(35, "Comedy");
        seriesGenresMap.put(80, "Crime");
        seriesGenresMap.put(99, "Documentary");
        seriesGenresMap.put(18, "Drama");
        seriesGenresMap.put(10751, "Family");
        seriesGenresMap.put(10762, "Kids");
        seriesGenresMap.put(9648, "Mystery");
        seriesGenresMap.put(10763, "News");
        seriesGenresMap.put(10764, "Reality");
        seriesGenresMap.put(10765, "Sci-Fi & Fantasy");
        seriesGenresMap.put(10766, "Soap");
        seriesGenresMap.put(10767, "Talk");
        seriesGenresMap.put(10768, "War & Politics");
        seriesGenresMap.put(37, "Western");

    }

    public static String getMovieGenre(int genreId) {
        return movieGenresMap.getOrDefault(genreId, "Genre not found");
    }

    public static String getSeriesGenre(int genreId) {
        return seriesGenresMap.getOrDefault(genreId, "Genre not found");
    }
}
