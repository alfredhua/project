package com.pro.site.controller;

/**
 * @auth guozhenhua
 * @date 2018/11/14
 */
public class SiteWebsiteUrl {


  static final String WEBSITE_BASE_URL = "/site/website";

  /**
   * Banner
   */
  static final String LIST_BANNER_BY_TYPE = "/list-banner-by-type";

  /**
   * 网站设置相关
   */
  static final String LIST_SETTING="/list-setting";
  static final  String GET_SETTING="/get-setting/{type}";
  static final  String LIST_CHILDREN_SETTING="/list-children-setting/{type}";

  /**
   * 产品服务相关
   */
  static final String LIST_PRODUCE_HOME = "/list-produce-home";
  static final String LIST_ALL_PRODUCE = "/list-all-produce";
  static final String GET_PRODUCE = "/get-produce/{id}";

  /**
   * 新闻相关
   */
  static final String LIST_NEWS_HOME = "/list-news-home";
  static  final  String LIST_NEWS_PAGE="/list-news-page";
  static final String GET_NEWS_BY_ID ="/get-news/{id}" ;

  /**
   * 合作伙伴相关
   */
  static final String LIST_ALL_PARTNER = "list-all-partner";

  /**
   * 公告类型
   */
  static final String LIST_ACTIVE_NOTICE_TYPE ="list-active-notice-type";
  static final String LIST_NOTICE="list-notice";
  static final String GET_NOTICE = "/get-notice/{id}";


  static final String BLOG_BASE_URL = "/site/blog";

  static final String GET_ARTICLE = "/get-article";
  static final String LIST_ARTICLE = "/list-article";
  static final String CLICK_CHARTS="/click-charts";
  static final String LIST_ARTICLE_HOME="/list-article-home";
  static final String SEARCH="/list-search";



  static final String CREATE_ARTICLE_COMMENT = "/create-article-comment";
  static final String LIST_ARTICLE_COMMENT = "/list-article-comment";
  static final String LIST_COMMENT_BY_ARTICLE_ID = "/list-comment-by-article-id/{articleId}";

  static final String LIST_ALL_TYPE = "/list-all-active-type";
  static final String GET_TYPE="/get-type";

}
