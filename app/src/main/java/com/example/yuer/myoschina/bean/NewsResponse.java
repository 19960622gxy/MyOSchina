package com.example.yuer.myoschina.bean;

import java.util.List;

/**
 * Created by Yuer on 2017/4/27.
 */

public class NewsResponse {

    /**
     * newslist : [{"author":"局长","id":84240,"title":"TensorFlow 1.1.0 发布，添加部分语言的安装指南","type":4,"authorid":2720166,"pubDate":"2017-04-27 08:21:37","commentCount":9},{"author":"王练","id":84239,"title":"5月上海&南京源创会，Apache Eagle 架构演化和新特性","type":0,"authorid":2896879,"pubDate":"2017-04-27 08:18:36","url":"https://www.oschina.net/news/84136/2017-5-yue-yuanchuanghui","object":0,"commentCount":0},{"author":"王练","id":84238,"title":"只会增删查改？你还缺个数据库管理工具！","type":3,"authorid":2896879,"pubDate":"2017-04-27 08:12:50","commentCount":23,"object":887107},{"author":"王练","id":84237,"title":"码云推荐 | 用 Golang 编写的多功能编译小助手","type":0,"authorid":2896879,"pubDate":"2017-04-27 08:11:39","url":"https://git.oschina.net/voidint/gbb","object":0,"commentCount":0},{"author":"王练","id":84236,"title":"每日一博 | 前端工程师必备，JavaScript 异步编程","type":3,"authorid":2896879,"pubDate":"2017-04-27 08:09:26","commentCount":5,"object":887335},{"author":"王练","id":84235,"title":"jDialects \u2014\u2014 支持多数据库方言的原生 SQL 分页工具","type":1,"authorid":2896879,"pubDate":"2017-04-27 08:07:01","object":45164,"commentCount":0},{"author":"王练","id":84234,"title":"OSChina 周四乱弹 \u2014\u2014士可杀不可辱用英语怎么说？","type":3,"authorid":2896879,"pubDate":"2017-04-27 08:05:14","commentCount":38,"object":888003},{"author":"局长","id":84233,"title":"Ubuntu 17.10 每日构建 ISO 发布，仍使用 Unity 7 桌面环境","type":4,"authorid":2720166,"pubDate":"2017-04-27 08:04:29","commentCount":5},{"author":"王练","id":84232,"title":"携程开源 Redis 多数据中心复制管理系统 X-Pipe","type":4,"authorid":2896879,"pubDate":"2017-04-27 08:02:56","commentCount":2},{"author":"局长","id":84231,"title":"ECharts 3.5.4 发布，JavaScript 图表库","type":4,"authorid":2720166,"pubDate":"2017-04-27 07:58:56","commentCount":2},{"author":"王练","id":84229,"title":"Metron & Fineract 双双升级成 Apache 顶级项目","type":4,"authorid":2896879,"pubDate":"2017-04-27 07:56:18","commentCount":0},{"author":"王练","id":84228,"title":"Linux 基金会发布新资源，帮助理解和正确使用开源协议","type":4,"authorid":2896879,"pubDate":"2017-04-27 07:53:00","commentCount":7},{"author":"局长","id":84227,"title":"Ionic 3.1.0 发布，HTML5 移动应用框架","type":4,"authorid":2720166,"pubDate":"2017-04-27 07:50:54","commentCount":3},{"author":"王练","id":84226,"title":"恶意软件通过 Google Play 感染数百万 Android 用户","type":4,"authorid":2896879,"pubDate":"2017-04-27 07:50:31","commentCount":4},{"author":"局长","id":84224,"title":"Angular 4.1.0 正式发布，Web 前端框架","type":4,"authorid":2720166,"pubDate":"2017-04-27 07:33:13","commentCount":12},{"author":"局长","id":84223,"title":"GitHub Atom 1.17.0-beta4 发布，Bug 修复版本","type":4,"authorid":2720166,"pubDate":"2017-04-27 07:26:25","commentCount":4},{"author":"局长","id":84222,"title":"OrientDB 2.2.19 发布，可伸缩的文档数据库","type":4,"authorid":2720166,"pubDate":"2017-04-27 07:20:48","commentCount":0},{"author":"局长","id":84221,"title":"GitLab 9.1.1 发布，代码托管平台","type":4,"authorid":2720166,"pubDate":"2017-04-27 07:08:15","commentCount":2},{"author":"局长","id":84220,"title":"Apache FtpServer 1.1.1 发布，纯 Java 的 FTP 服务器软件","type":4,"authorid":2720166,"pubDate":"2017-04-27 07:02:14","commentCount":4},{"author":"jackygurui","id":84219,"title":"Redisson 3.4.0 和 2.9.0 发布，Redis 客户端","type":4,"authorid":2984351,"pubDate":"2017-04-27 06:57:11","commentCount":4}]
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private NoticeBean notice;
    private List<NewslistBean> newslist;

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
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

    public static class NewslistBean {
        /**
         * author : 局长
         * id : 84240
         * title : TensorFlow 1.1.0 发布，添加部分语言的安装指南
         * type : 4
         * authorid : 2720166
         * pubDate : 2017-04-27 08:21:37
         * commentCount : 9
         * url : https://www.oschina.net/news/84136/2017-5-yue-yuanchuanghui
         * object : 0
         */

        private String author;
        private int id;
        private String title;
        private int type;
        private int authorid;
        private String pubDate;
        private int commentCount;
        private String url;
        private int object;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getAuthorid() {
            return authorid;
        }

        public void setAuthorid(int authorid) {
            this.authorid = authorid;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getObject() {
            return object;
        }

        public void setObject(int object) {
            this.object = object;
        }
    }
}
