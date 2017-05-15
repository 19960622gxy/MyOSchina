package com.example.yuer.myoschina.bean;

/**
 * Created by Yuer on 2017/5/9.
 */

public class DongDanDetailResponse {

    /**
     * author : 某评伽
     * id : 13209368
     * portrait : https://www.oschina.net/img/portrait.gif
     * authorid : 187489
     * body : 别的域名也能访问我的网站是怎么回事？ 原来是没有禁用空主机头：
     <a href="http://www.youyong.top/article/1158e5a122d2" target="_blank" rel="nofollow">http://www.youyong.top/article/1158e5a122d2</a>
     * pubDate : 2017-05-09 10:23:43
     * commentCount : 0
     */

    private String author;
    private int id;
    private String portrait;
    private int authorid;
    private String body;
    private String pubDate;
    private int commentCount;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
