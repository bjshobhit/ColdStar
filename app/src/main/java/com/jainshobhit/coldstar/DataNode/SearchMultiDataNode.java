package com.jainshobhit.coldstar.DataNode;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

public class SearchMultiDataNode implements Serializable {
  private Integer page;

  private Integer total_pages;

  private List<Results> results;

  private Integer total_results;

  public Integer getPage() {
    return this.page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getTotal_pages() {
    return this.total_pages;
  }

  public void setTotal_pages(Integer total_pages) {
    this.total_pages = total_pages;
  }

  public List<Results> getResults() {
    return this.results;
  }

  public void setResults(List<Results> results) {
    this.results = results;
  }

  public Integer getTotal_results() {
    return this.total_results;
  }

  public void setTotal_results(Integer total_results) {
    this.total_results = total_results;
  }

  public static class Results implements Serializable {
    private String overview;

    private String original_language;

    private String original_title;

    private Boolean video;

    private String title;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    private String name;

    private List<Integer> genre_ids;

    private String poster_path;

    private String backdrop_path;

    public String getFirst_air_date() {
      return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
      this.first_air_date = first_air_date;
    }

    private String first_air_date;

    private String media_type;

    private String release_date;

    private Double popularity;

    private Double vote_average;

    private Integer id;

    private Boolean adult;

    private Integer vote_count;

    public String getOverview() {
      return this.overview;
    }

    public void setOverview(String overview) {
      this.overview = overview;
    }

    public String getOriginal_language() {
      return this.original_language;
    }

    public void setOriginal_language(String original_language) {
      this.original_language = original_language;
    }

    public String getOriginal_title() {
      return this.original_title;
    }

    public void setOriginal_title(String original_title) {
      this.original_title = original_title;
    }

    public Boolean getVideo() {
      return this.video;
    }

    public void setVideo(Boolean video) {
      this.video = video;
    }

    public String getTitle() {
      return this.title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public List<Integer> getGenre_ids() {
      return this.genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
      this.genre_ids = genre_ids;
    }

    public String getPoster_path() {
      return this.poster_path;
    }

    public void setPoster_path(String poster_path) {
      this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
      return this.backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
      this.backdrop_path = backdrop_path;
    }

    public String getMedia_type() {
      return this.media_type;
    }

    public void setMedia_type(String media_type) {
      this.media_type = media_type;
    }

    public String getRelease_date() {
      return this.release_date;
    }

    public void setRelease_date(String release_date) {
      this.release_date = release_date;
    }

    public Double getPopularity() {
      return this.popularity;
    }

    public void setPopularity(Double popularity) {
      this.popularity = popularity;
    }

    public Double getVote_average() {
      return this.vote_average;
    }

    public void setVote_average(Double vote_average) {
      this.vote_average = vote_average;
    }

    public Integer getId() {
      return this.id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public Boolean getAdult() {
      return this.adult;
    }

    public void setAdult(Boolean adult) {
      this.adult = adult;
    }

    public Integer getVote_count() {
      return this.vote_count;
    }

    public void setVote_count(Integer vote_count) {
      this.vote_count = vote_count;
    }
  }
}
