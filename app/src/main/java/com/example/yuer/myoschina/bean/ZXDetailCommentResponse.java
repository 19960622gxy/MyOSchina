package com.example.yuer.myoschina.bean;

import java.util.List;

/**
 * Created by Yuer on 2017/5/9.
 */

public class ZXDetailCommentResponse {

    /**
     * commentList : [{"commentPortrait":"https://static.oschina.net/uploads/user/307/615101_50.jpeg?t=1489391399000","commentAuthorId":615101,"commentAuthor":"进无止境_云上飞","id":13213426,"client_type":0,"pubDate":"2017-05-09 14:13:21","content":"<a href=\"https://my.oschina.net/wintelsui\">@紫怨<\/a> 充气效果怎样"},{"commentPortrait":"https://static.oschina.net/uploads/user/307/615101_50.jpeg?t=1489391399000","commentAuthorId":615101,"commentAuthor":"进无止境_云上飞","id":13213407,"client_type":0,"pubDate":"2017-05-09 14:12:41","content":"<a href=\"https://my.oschina.net/u/1047640\">@一只小桃子<\/a> +1"},{"commentPortrait":"https://static.oschina.net/uploads/user/472/945123_50.jpg?t=1392349103000","commentAuthorId":945123,"commentAuthor":"汪超","id":13213347,"client_type":0,"pubDate":"2017-05-09 14:10:35","content":"今天你动弹了吗？"},{"commentPortrait":"https://static.oschina.net/uploads/user/523/1047640_50.jpg?t=1427780537000","commentAuthorId":1047640,"commentAuthor":"一只小桃子","id":13213235,"client_type":0,"pubDate":"2017-05-09 14:06:03","content":"那你现在应该死心了，钱确实可以买到喜欢的人"},{"commentPortrait":"https://static.oschina.net/uploads/user/365/730880_50.jpg?t=1484213923000","commentAuthorId":730880,"commentAuthor":"紫怨","id":13213215,"client_type":0,"pubDate":"2017-05-09 14:05:24","content":"考虑一下普通款吧,也还是不错的,那种有力反馈啊,语音的啊,不仅贵,还费电"}]
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private NoticeBean notice;
    private List<CommentListBean> commentList;

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<CommentListBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentListBean> commentList) {
        this.commentList = commentList;
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

    public static class CommentListBean {
        /**
         * commentPortrait : https://static.oschina.net/uploads/user/307/615101_50.jpeg?t=1489391399000
         * commentAuthorId : 615101
         * commentAuthor : 进无止境_云上飞
         * id : 13213426
         * client_type : 0
         * pubDate : 2017-05-09 14:13:21
         * content : <a href="https://my.oschina.net/wintelsui">@紫怨</a> 充气效果怎样
         */

        private String commentPortrait;
        private int commentAuthorId;
        private String commentAuthor;
        private int id;
        private int client_type;
        private String pubDate;
        private String content;

        public String getCommentPortrait() {
            return commentPortrait;
        }

        public void setCommentPortrait(String commentPortrait) {
            this.commentPortrait = commentPortrait;
        }

        public int getCommentAuthorId() {
            return commentAuthorId;
        }

        public void setCommentAuthorId(int commentAuthorId) {
            this.commentAuthorId = commentAuthorId;
        }

        public String getCommentAuthor() {
            return commentAuthor;
        }

        public void setCommentAuthor(String commentAuthor) {
            this.commentAuthor = commentAuthor;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getClient_type() {
            return client_type;
        }

        public void setClient_type(int client_type) {
            this.client_type = client_type;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
