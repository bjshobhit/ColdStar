package com.jainshobhit.coldstar.DataNode;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

public class ImagesNode implements Serializable {
  private List<Backdrops> backdrops;

  private List<Backdrops> posters;

  private Integer id;

  private List<Backdrops> logos;

  public List<Backdrops> getBackdrops() {
    return this.backdrops;
  }

  public void setBackdrops(List<Backdrops> backdrops) {
    this.backdrops = backdrops;
  }

  public List<Backdrops> getPosters() {
    return this.posters;
  }

  public void setPosters(List<Backdrops> posters) {
    this.posters = posters;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Backdrops> getLogos() {
    return this.logos;
  }

  public void setLogos(List<Backdrops> logos) {
    this.logos = logos;
  }

  public static class Backdrops implements Serializable {
    private Double aspect_ratio;

    private String file_path;

    private Double vote_average;

    private Integer width;

    private String iso_639_1;

    private Integer vote_count;

    private Integer height;

    public Double getAspect_ratio() {
      return this.aspect_ratio;
    }

    public void setAspect_ratio(Double aspect_ratio) {
      this.aspect_ratio = aspect_ratio;
    }

    public String getFile_path() {
      return this.file_path;
    }

    public void setFile_path(String file_path) {
      this.file_path = file_path;
    }

    public Double getVote_average() {
      return this.vote_average;
    }

    public void setVote_average(Double vote_average) {
      this.vote_average = vote_average;
    }

    public Integer getWidth() {
      return this.width;
    }

    public void setWidth(Integer width) {
      this.width = width;
    }

    public String getIso_639_1() {
      return this.iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
      this.iso_639_1 = iso_639_1;
    }

    public Integer getVote_count() {
      return this.vote_count;
    }

    public void setVote_count(Integer vote_count) {
      this.vote_count = vote_count;
    }

    public Integer getHeight() {
      return this.height;
    }

    public void setHeight(Integer height) {
      this.height = height;
    }
  }
}
