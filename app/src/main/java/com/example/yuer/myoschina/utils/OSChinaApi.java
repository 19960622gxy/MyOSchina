package com.example.yuer.myoschina.utils;

/**
 * Created by Yuer on 2017/4/27.
 */

public class OSChinaApi {
    public  static  final  String HOST = "http://www.oschina.net"; //主机地址

    public  static  final  String TOKEN = HOST+ "/action/openapi/token";

    public  static  final  String NEWS_LIST = HOST+ "/action/openapi/news_list";

    public  static  final  String BLOG_RECOMMEND_LIST = HOST+ "/action/openapi/blog_recommend_list";

    public  static  final  String POST_LIST = HOST+ "/action/openapi/post_list";


    public  static  final  String TWEET_LIST = HOST+ "/action/openapi/tweet_list";


    public  static  final  String NEWS_DETAIL = HOST+ "/action/openapi/news_detail";

    //发表评论
    public  static  final  String COMMENT_PUB = HOST+ "/action/openapi/comment_pub";

    //获取评论列表
    public  static  final  String COMMENT_LIST = HOST+ "/action/openapi/comment_list";

    //动弹详情
    public  static  final  String DONGDAN_DETAIL = HOST+ "/action/openapi/tweet_detail";

    //获取当前登录用户的账户信息
    public  static  final  String USER = HOST+ "/action/openapi/user";

    //添加收藏
    public  static  final  String FAVORITE_ADD = HOST+ "/action/openapi/favorite_add";

    //个人主页详情
    public  static  final  String MY_INFORMATION = HOST+ "/action/openapi/my_information";

    //获取软件分类列表(只有2级)
    public  static  final  String PROJIECT_CATALOG_LIST = HOST+ "/action/openapi/project_catalog_list";

    //软件分类下的的软件列表
    public  static  final  String PROJIECT_LIST = HOST+ "/action/openapi/project_list";

    //软件分类下的的软件列表
    public  static  final  String TWEET_PUB = HOST+ "/action/openapi/tweet_pub";



}

