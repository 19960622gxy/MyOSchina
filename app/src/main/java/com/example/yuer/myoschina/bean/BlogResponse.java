package com.example.yuer.myoschina.bean;

import java.util.List;

/**
 * Created by Yuer on 2017/5/2.
 */

public class BlogResponse {

    /**
     * bloglist : [{"author":"听风的小猪","id":890286,"title":"Python迭代器与生成器","authorid":2764829,"type":1,"pubDate":"2017-05-02 14:27:37.0","commentCount":0},{"author":"naughty","id":890269,"title":"使用开源Echarts为Splunk打造类似语法驱动的分析可视化","authorid":1450051,"type":1,"pubDate":"2017-05-02 14:02:14.0","commentCount":2},{"author":"程序猿DD","id":890237,"title":"Spring Cloud实战小贴士：Zuul处理Cookie和重定向","authorid":2653610,"type":1,"pubDate":"2017-05-02 12:46:41.0","commentCount":0},{"author":"葡萄城控件技术团队","id":890227,"title":"Angular vs React 最全面深入对比 ","authorid":1789896,"type":1,"pubDate":"2017-05-02 12:19:01.0","commentCount":2},{"author":"鉴客","id":890115,"title":"很多程序员忘记的十个简单的社交技巧","authorid":54100,"type":4,"pubDate":"2017-05-02 10:03:47.0","commentCount":1},{"author":"卢勇福","id":890095,"title":"一个节奏极快的创业公司的web前端持续交付心路历程","authorid":13535,"type":1,"pubDate":"2017-05-02 09:31:08.0","commentCount":0},{"author":"国栋","id":889992,"title":"劳动节纪念\u2014\u2014叹苦逼码农之古诗今改几篇汇总","authorid":1772009,"type":1,"pubDate":"2017-05-01 20:23:47.0","commentCount":0},{"author":"芋艿V","id":889983,"title":"RocketMQ源码解析：Message存储","authorid":210730,"type":1,"pubDate":"2017-05-01 19:47:04.0","commentCount":0},{"author":"voole","id":889972,"title":"网站统计功能的设计与实现","authorid":205735,"type":4,"pubDate":"2017-05-01 18:24:50.0","commentCount":0},{"author":"打雷要下雨LEO","id":889938,"title":"实战Chrome Headless数据抓取（上）","authorid":97481,"type":1,"pubDate":"2017-05-01 15:59:54.0","commentCount":0},{"author":"AbeJeffrey","id":889931,"title":"Java线程池核心实现原理和源码解析（ThreadPoolExecutor）","authorid":2663573,"type":1,"pubDate":"2017-05-01 15:43:03.0","commentCount":2},{"author":"飞奔的石头","id":889920,"title":"Ubuntu16.04LTS安装预编译gpu版tensorflow及mnist小实验","authorid":3459934,"type":1,"pubDate":"2017-05-01 14:49:11.0","commentCount":0},{"author":"开源中国码云","id":889890,"title":"【码云周刊第 18 期】假日照片堆积如何处理？这些工具你可能用得上","authorid":2267325,"type":1,"pubDate":"2017-05-01 11:46:44.0","commentCount":0},{"author":"talent-tan","id":889887,"title":"aio系列文档（2）----图解bytebuffer","authorid":175825,"type":1,"pubDate":"2017-05-01 11:37:05.0","commentCount":15},{"author":"MrXI","id":889859,"title":"Android设计模式\u2014\u2014策略模式之源码使用场景（三）","authorid":2011321,"type":1,"pubDate":"2017-05-01 10:38:16.0","commentCount":0},{"author":"Lohanry","id":889765,"title":"Jenkins中的Android打包任务设计","authorid":229840,"type":1,"pubDate":"2017-04-30 18:19:27.0","commentCount":0},{"author":"qiangdada","id":889656,"title":"合格前端系列系列第二弹-Vue组件开发续篇","authorid":2912341,"type":1,"pubDate":"2017-04-30 00:34:37.0","commentCount":4},{"author":"编辑部的故事","id":889587,"title":"【软件周刊第 26 期】2017 Percona Live 开源数据库大会介绍了 MySQL 8.0 的主要新功能","authorid":2918182,"type":1,"pubDate":"2017-04-29 18:52:40.0","commentCount":0},{"author":"MrXI","id":889482,"title":"棒棒糖之\u2014\u2014Android中视图的绘制流程","authorid":2011321,"type":1,"pubDate":"2017-04-29 11:08:41.0","commentCount":0},{"author":"编辑部的故事","id":889451,"title":"【源资讯 第25期】一波开源项目将停止维护","authorid":2918182,"type":1,"pubDate":"2017-04-29 06:14:32.0","commentCount":0}]
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private NoticeBean notice;
    private List<BloglistBean> bloglist;

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<BloglistBean> getBloglist() {
        return bloglist;
    }

    public void setBloglist(List<BloglistBean> bloglist) {
        this.bloglist = bloglist;
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

    public static class BloglistBean {
        /**
         * author : 听风的小猪
         * id : 890286
         * title : Python迭代器与生成器
         * authorid : 2764829
         * type : 1
         * pubDate : 2017-05-02 14:27:37.0
         * commentCount : 0
         */

        private String author;
        private int id;
        private String title;
        private int authorid;
        private int type;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getAuthorid() {
            return authorid;
        }

        public void setAuthorid(int authorid) {
            this.authorid = authorid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
}
