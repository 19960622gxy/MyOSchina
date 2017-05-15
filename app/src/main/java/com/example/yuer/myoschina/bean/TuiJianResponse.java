package com.example.yuer.myoschina.bean;

import java.util.List;

/**
 * Created by Yuer on 2017/5/15.
 */

public class TuiJianResponse {

    /**
     * projectlist : [{"name":"Linkerd","description":"Linkerd 是一个提供弹性云端原生应用服...","url":"https://www.oschina.net/p/linkerd"},{"name":"EverVim","description":"EverVim: 一个面向所有开发者的 Vim 发行...","url":"https://www.oschina.net/p/evervim"},{"name":"Tars","description":"Tars 是基于名字服务使用 Tars 协议的高...","url":"https://www.oschina.net/p/tars"},{"name":"Neuro.js","description":"Neuro.js 是浏览器中进行深度学习的 Ja...","url":"https://www.oschina.net/p/neuro-js"},{"name":"Meinheld","description":"Meinheld 是一个高性能的异步 WSGI Web...","url":"https://www.oschina.net/p/meinheld"},{"name":"GraphQL","description":"GraphQL 是一个由Facebook提出的 应用层...","url":"https://www.oschina.net/p/graphql"},{"name":"Rancher","description":"Rancher 是一个开源的项目，提供了在产品...","url":"https://www.oschina.net/p/rancher"},{"name":"DockerFly","description":"Dockerfly 是基于 Docker1.12+ (Docker ...","url":"https://www.oschina.net/p/dockerfly"},{"name":"ProxySQL","description":"ProxySQL  ProxySQL 是一个高性能，高可...","url":"https://www.oschina.net/p/proxysql"},{"name":"CodeCloud.VisualStudio","description":"CodeCloud.VisualStudio 是码云 Visual...","url":"https://www.oschina.net/p/codecloud-visualstudio"},{"name":"Redash","description":"re:dash 是一款开源的BI工具，提供了基于...","url":"https://www.oschina.net/p/redash"},{"name":"Apache Beam","description":"Apache Beam 是 Apache 软件基金会越来越...","url":"https://www.oschina.net/p/apachebeam"},{"name":"Zeppelin","description":"Apache Zeppelin  是一个让交互式数据分...","url":"https://www.oschina.net/p/zeppelin"},{"name":"Big Bang","description":"Big Bang   锤子科技的一小步，智能手机...","url":"https://www.oschina.net/p/big-bang"},{"name":"Guacamole","description":"Guacamole 是一个基于 HTML 5 和 JavaSc...","url":"https://www.oschina.net/p/guacamole"},{"name":"Mars","description":"Mars 是微信官方的终端基础组件，是一个...","url":"https://www.oschina.net/p/wechat-mars"},{"name":"One Step","description":"一步（One Step）是由锤子科技开源的 An...","url":"https://www.oschina.net/p/one-step"},{"name":"DeepMind Lab","description":"DeepMind Lab 是一个 3D 的学习环境，基...","url":"https://www.oschina.net/p/deepmind+lab"},{"name":"Druid-IO","description":"Druid 是一个开源的专为事件数据的 OLAP...","url":"https://www.oschina.net/p/druid-io"},{"name":"TestCafe","description":"TestCafe TestCafe是一个用于测试Web应用...","url":"https://www.oschina.net/p/testcafe"}]
     * count : 371
     */

    private int count;
    private List<ProjectlistBean> projectlist;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProjectlistBean> getProjectlist() {
        return projectlist;
    }

    public void setProjectlist(List<ProjectlistBean> projectlist) {
        this.projectlist = projectlist;
    }

    public static class ProjectlistBean {
        /**
         * name : Linkerd
         * description : Linkerd 是一个提供弹性云端原生应用服...
         * url : https://www.oschina.net/p/linkerd
         */

        private String name;
        private String description;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
