package com.example.yuer.myoschina.bean;

import java.util.List;

/**
 * Created by Yuer on 2017/5/13.
 */

public class MyInformationResponse
{

    /**
     * gender : 2
     * joinTime : 2017-04-27 09:20:58
     * city : 南京
     * fansCount : 0
     * portrait : https://static.oschina.net/uploads/user/1726/3453639_50.jpg?t=1494227369000
     * expertise : []
     * platforms : []
     * uid : 3453639
     * lastLoginTime : 2017-05-13 19:39:36
     * province : 江苏
     * name : 成住坏空
     * followersCount : 0
     * favoriteCount : 3
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private int gender;
    private String joinTime;
    private String city;
    private int fansCount;
    private String portrait;
    private int uid;
    private String lastLoginTime;
    private String province;
    private String name;
    private int followersCount;
    private int favoriteCount;
    private NoticeBean notice;
    private List<?> expertise;
    private List<?> platforms;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<?> getExpertise() {
        return expertise;
    }

    public void setExpertise(List<?> expertise) {
        this.expertise = expertise;
    }

    public List<?> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<?> platforms) {
        this.platforms = platforms;
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
