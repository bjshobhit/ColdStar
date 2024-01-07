package com.jainshobhit.coldstar.DataNode;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.List;

public class SeasonsDetailsNode implements Serializable {
  private String air_date;

  private String overview;

  private Double vote_average;

  private String name;

  private Integer season_number;

  private String _id;

  private Integer id;

  private List<Episodes> episodes;

  private String poster_path;

  public String getAir_date() {
    return this.air_date;
  }

  public void setAir_date(String air_date) {
    this.air_date = air_date;
  }

  public String getOverview() {
    return this.overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public Double getVote_average() {
    return this.vote_average;
  }

  public void setVote_average(Double vote_average) {
    this.vote_average = vote_average;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSeason_number() {
    return this.season_number;
  }

  public void setSeason_number(Integer season_number) {
    this.season_number = season_number;
  }

  public String get_id() {
    return this._id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Episodes> getEpisodes() {
    return this.episodes;
  }

  public void setEpisodes(List<Episodes> episodes) {
    this.episodes = episodes;
  }

  public String getPoster_path() {
    return this.poster_path;
  }

  public void setPoster_path(String poster_path) {
    this.poster_path = poster_path;
  }

  public static class Episodes implements Serializable {
    private String episode_type;

    private String production_code;

    private String overview;

    private Integer show_id;

    private Integer season_number;

    private Integer runtime;

    private String still_path;

    private List<Crew> crew;

    private List<Guest_stars> guest_stars;

    private String air_date;

    private Integer episode_number;

    private Double vote_average;

    private String name;

    private Integer id;

    private Integer vote_count;

    public String getEpisode_type() {
      return this.episode_type;
    }

    public void setEpisode_type(String episode_type) {
      this.episode_type = episode_type;
    }

    public String getProduction_code() {
      return this.production_code;
    }

    public void setProduction_code(String production_code) {
      this.production_code = production_code;
    }

    public String getOverview() {
      return this.overview;
    }

    public void setOverview(String overview) {
      this.overview = overview;
    }

    public Integer getShow_id() {
      return this.show_id;
    }

    public void setShow_id(Integer show_id) {
      this.show_id = show_id;
    }

    public Integer getSeason_number() {
      return this.season_number;
    }

    public void setSeason_number(Integer season_number) {
      this.season_number = season_number;
    }

    public Integer getRuntime() {
      return this.runtime;
    }

    public void setRuntime(Integer runtime) {
      this.runtime = runtime;
    }

    public String getStill_path() {
      return this.still_path;
    }

    public void setStill_path(String still_path) {
      this.still_path = still_path;
    }

    public List<Crew> getCrew() {
      return this.crew;
    }

    public void setCrew(List<Crew> crew) {
      this.crew = crew;
    }

    public List<Guest_stars> getGuest_stars() {
      return this.guest_stars;
    }

    public void setGuest_stars(List<Guest_stars> guest_stars) {
      this.guest_stars = guest_stars;
    }

    public String getAir_date() {
      return this.air_date;
    }

    public void setAir_date(String air_date) {
      this.air_date = air_date;
    }

    public Integer getEpisode_number() {
      return this.episode_number;
    }

    public void setEpisode_number(Integer episode_number) {
      this.episode_number = episode_number;
    }

    public Double getVote_average() {
      return this.vote_average;
    }

    public void setVote_average(Double vote_average) {
      this.vote_average = vote_average;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getId() {
      return this.id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public Integer getVote_count() {
      return this.vote_count;
    }

    public void setVote_count(Integer vote_count) {
      this.vote_count = vote_count;
    }

    public static class Crew implements Serializable {
      private Integer gender;

      private String credit_id;

      private String known_for_department;

      private String original_name;

      private Double popularity;

      private String name;

      private Object profile_path;

      private Integer id;

      private String department;

      private String job;

      private Boolean adult;

      public Integer getGender() {
        return this.gender;
      }

      public void setGender(Integer gender) {
        this.gender = gender;
      }

      public String getCredit_id() {
        return this.credit_id;
      }

      public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
      }

      public String getKnown_for_department() {
        return this.known_for_department;
      }

      public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
      }

      public String getOriginal_name() {
        return this.original_name;
      }

      public void setOriginal_name(String original_name) {
        this.original_name = original_name;
      }

      public Double getPopularity() {
        return this.popularity;
      }

      public void setPopularity(Double popularity) {
        this.popularity = popularity;
      }

      public String getName() {
        return this.name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public Object getProfile_path() {
        return this.profile_path;
      }

      public void setProfile_path(Object profile_path) {
        this.profile_path = profile_path;
      }

      public Integer getId() {
        return this.id;
      }

      public void setId(Integer id) {
        this.id = id;
      }

      public String getDepartment() {
        return this.department;
      }

      public void setDepartment(String department) {
        this.department = department;
      }

      public String getJob() {
        return this.job;
      }

      public void setJob(String job) {
        this.job = job;
      }

      public Boolean getAdult() {
        return this.adult;
      }

      public void setAdult(Boolean adult) {
        this.adult = adult;
      }
    }

    public static class Guest_stars implements Serializable {
      private String character;

      private Integer gender;

      private String credit_id;

      private String known_for_department;

      private String original_name;

      private Double popularity;

      private String name;

      private String profile_path;

      private Integer id;

      private Boolean adult;

      private Integer order;

      public String getCharacter() {
        return this.character;
      }

      public void setCharacter(String character) {
        this.character = character;
      }

      public Integer getGender() {
        return this.gender;
      }

      public void setGender(Integer gender) {
        this.gender = gender;
      }

      public String getCredit_id() {
        return this.credit_id;
      }

      public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
      }

      public String getKnown_for_department() {
        return this.known_for_department;
      }

      public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
      }

      public String getOriginal_name() {
        return this.original_name;
      }

      public void setOriginal_name(String original_name) {
        this.original_name = original_name;
      }

      public Double getPopularity() {
        return this.popularity;
      }

      public void setPopularity(Double popularity) {
        this.popularity = popularity;
      }

      public String getName() {
        return this.name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getProfile_path() {
        return this.profile_path;
      }

      public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
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

      public Integer getOrder() {
        return this.order;
      }

      public void setOrder(Integer order) {
        this.order = order;
      }
    }
  }
}
