package com.example.yuer.myoschina.bean;

import java.util.List;

/**
 * Created by Yuer on 2017/5/15.
 */

public class KaiYuanResponse {

    /**
     * softwareTypes : [{"name":"编程语言","tag":1},{"name":"操作系统","tag":3},{"name":"适应人员角色","tag":2},{"name":"Web应用开发","tag":309},{"name":"手机/移动开发","tag":331},{"name":"iOS代码库","tag":364},{"name":"程序开发","tag":12},{"name":"开发工具","tag":11},{"name":"jQuery 插件","tag":273},{"name":"建站系统","tag":256},{"name":"企业应用","tag":5},{"name":"服务器软件","tag":10},{"name":"数据库相关","tag":6},{"name":"应用工具","tag":8},{"name":"插件和扩展","tag":18},{"name":"游戏/娱乐","tag":7},{"name":"管理和监控","tag":14},{"name":"其他开源","tag":9}]
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private NoticeBean notice;
    private List<SoftwareTypesBean> softwareTypes;

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<SoftwareTypesBean> getSoftwareTypes() {
        return softwareTypes;
    }

    public void setSoftwareTypes(List<SoftwareTypesBean> softwareTypes) {
        this.softwareTypes = softwareTypes;
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

    public static class SoftwareTypesBean {
        /**
         * name : 编程语言
         * tag : 1
         */

        private String name;
        private int tag;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }
    }
}
