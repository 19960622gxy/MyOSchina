package com.example.yuer.myoschina.bean;

/**
 * Created by Yuer on 2017/5/4.
 */

public class ScrollImageBean {
    private String title;
    private int imageId;
//    private String url;

    public ScrollImageBean(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
