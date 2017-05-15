package com.example.yuer.myoschina.bean;

import java.util.List;

/**
 * Created by Yuer on 2017/5/8.
 */

public class NewsCommentResponse {

    /**
     * commentList : [{"replies":[{"rpubDate":"2014-03-10 11:46:08","rcontent":"最新的版本支持你说的功能吗？通过传参调度","rauthorId":814396,"rauthor":"小草一号"}],"commentPortrait":"https://static.oschina.net/uploads/user/101/203191_50.jpg?t=1368809637000","commentAuthorId":203191,"commentAuthor":"绝望的八皮","id":390168,"client_type":1,"pubDate":"2013-01-05 16:31:07","content":"目前quartz plugin只封装了简单的功能。利用表达式去定时调度，还不支持传递参数等操作。因为之前我也没有用到这些高级一点的功能。如果你有需要可以改造。"},{"replies":[{"rpubDate":"2012-12-24 13:12:48","rcontent":"多谢 一级解决 think you","rauthorId":260631,"rauthor":"沉寂brain"}],"commentPortrait":"https://www.oschina.net/img/portrait.gif","commentAuthorId":256271,"commentAuthor":"alvinte","id":380771,"client_type":1,"pubDate":"2012-12-24 11:05:48","content":"Jfinal-ext里面有quartz的整合。http://www.oschina.net/p/jfinal-ext"}]
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
         * replies : [{"rpubDate":"2014-03-10 11:46:08","rcontent":"最新的版本支持你说的功能吗？通过传参调度","rauthorId":814396,"rauthor":"小草一号"}]
         * commentPortrait : https://static.oschina.net/uploads/user/101/203191_50.jpg?t=1368809637000
         * commentAuthorId : 203191
         * commentAuthor : 绝望的八皮
         * id : 390168
         * client_type : 1
         * pubDate : 2013-01-05 16:31:07
         * content : 目前quartz plugin只封装了简单的功能。利用表达式去定时调度，还不支持传递参数等操作。因为之前我也没有用到这些高级一点的功能。如果你有需要可以改造。
         */

        private String commentPortrait;
        private int commentAuthorId;
        private String commentAuthor;
        private int id;
        private int client_type;
        private String pubDate;
        private String content;
        private List<RepliesBean> replies;

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

        public List<RepliesBean> getReplies() {
            return replies;
        }

        public void setReplies(List<RepliesBean> replies) {
            this.replies = replies;
        }

        public static class RepliesBean {
            /**
             * rpubDate : 2014-03-10 11:46:08
             * rcontent : 最新的版本支持你说的功能吗？通过传参调度
             * rauthorId : 814396
             * rauthor : 小草一号
             */

            private String rpubDate;
            private String rcontent;
            private int rauthorId;
            private String rauthor;

            public String getRpubDate() {
                return rpubDate;
            }

            public void setRpubDate(String rpubDate) {
                this.rpubDate = rpubDate;
            }

            public String getRcontent() {
                return rcontent;
            }

            public void setRcontent(String rcontent) {
                this.rcontent = rcontent;
            }

            public int getRauthorId() {
                return rauthorId;
            }

            public void setRauthorId(int rauthorId) {
                this.rauthorId = rauthorId;
            }

            public String getRauthor() {
                return rauthor;
            }

            public void setRauthor(String rauthor) {
                this.rauthor = rauthor;
            }
        }
    }
}
