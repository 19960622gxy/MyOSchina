package com.example.yuer.myoschina.bean;

import java.util.List;

/**
 * Created by Yuer on 2017/5/8.
 */

public class NewsDetailResponse {

    /**
     * author : 达尔文
     * id : 84556
     * authorid : 2903254
     * title : 5月上海&南京源创会，华为大牛畅聊 EPC 网络能力开放
     * body : <style type='text/css'>pre {white-space:pre-wrap;word-wrap:break-word;}</style><p>随着移动互联网的发展，OTT应用得到了爆炸性增长，逐渐取代移动运营商的语音和短信，成为主要的移动业务。运营商为避免沦为管道提供商，迫切需要与OTT SP以及企业集团合作，实现运营商、用户、OTT/企业集团的多赢。华为EPC网络能力开放解决方案正是为解决运营商、OTT厂商、终端用户的这些需求而生。</p>
     * pubDate : 2017-05-08 07:56:16
     * favorite : 0
     * url : https://www.oschina.net/news/84136/2017-5-yue-yuanchuanghui
     * relativies : []
     * commentCount : 0
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private String author;
    private int id;
    private int authorid;
    private String title;
    private String body;
    private String pubDate;
    private int favorite;
    private String url;
    private int commentCount;
    private NoticeBean notice;
    private List<?> relativies;

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

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<?> getRelativies() {
        return relativies;
    }

    public void setRelativies(List<?> relativies) {
        this.relativies = relativies;
    }

    public static class NoticeBean {
        /**
         * referCount : 0
         * replyCount : 0
         * msgCount : 0
         * fansCount : 0
         */

        private int referCount;
        private int replyCount;
        private int msgCount;
        private int fansCount;

        public int getReferCount() {
            return referCount;
        }

        public void setReferCount(int referCount) {
            this.referCount = referCount;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getMsgCount() {
            return msgCount;
        }

        public void setMsgCount(int msgCount) {
            this.msgCount = msgCount;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }
    }
}
