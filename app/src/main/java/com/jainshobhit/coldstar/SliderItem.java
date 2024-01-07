package com.jainshobhit.coldstar;

public class SliderItem {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String backImage,titleImage,title;
    boolean isMovie;

    public boolean isMovie() {
        return isMovie;
    }

    public void setMovie(boolean movie) {
        isMovie = movie;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SliderItem(int id, String backImage, String titleImage, String title, boolean isMovie) {
        this.id = id;
        this.backImage = backImage;
        this.titleImage = titleImage;
        this.title = title;
        this.isMovie=isMovie;
    }
}
