package com.jainshobhit.coldstar.DataNode;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

public class VideosNode implements Serializable {
  private Integer id;

  private List<Results> results;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Results> getResults() {
    return this.results;
  }

  public void setResults(List<Results> results) {
    this.results = results;
  }

  public static class Results implements Serializable {
    private String site;

    private Integer size;

    private String iso_3166_1;

    private String name;

    private Boolean official;

    private String id;

    private String type;

    private String published_at;

    private String iso_639_1;

    private String key;

    public String getSite() {
      return this.site;
    }

    public void setSite(String site) {
      this.site = site;
    }

    public Integer getSize() {
      return this.size;
    }

    public void setSize(Integer size) {
      this.size = size;
    }

    public String getIso_3166_1() {
      return this.iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
      this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Boolean getOfficial() {
      return this.official;
    }

    public void setOfficial(Boolean official) {
      this.official = official;
    }

    public String getId() {
      return this.id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getType() {
      return this.type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getPublished_at() {
      return this.published_at;
    }

    public void setPublished_at(String published_at) {
      this.published_at = published_at;
    }

    public String getIso_639_1() {
      return this.iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
      this.iso_639_1 = iso_639_1;
    }

    public String getKey() {
      return this.key;
    }

    public void setKey(String key) {
      this.key = key;
    }
  }
}
