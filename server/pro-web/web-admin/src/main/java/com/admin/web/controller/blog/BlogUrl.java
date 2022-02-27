package com.admin.web.controller.blog;

/**
 * @auth guozhenhua
 * @date
 */
public class BlogUrl {


    static final String BASE_URL = "/admin/blog";

  static final String CREATE_ARTICLE = "/create-article";
  static final String UPDATE_ARTICLE = "/update-article";
  static final String UPDATE_ARTICLE_STATUS = "/update-article-status";
  static final String GET_ARTICLE = "/get-article/{id}";
  static final String DEL_ARTICLE = "/del-article/{id}";
  static final String LIST_ARTICLE = "/list-article";

  static final String CREATE_ARTICLE_COMMENT = "/create-article-comment";
  static final String UPDATE_ARTICLE_COMMENT = "/update-article-comment";
  static final String GET_ARTICLE_COMMENT = "/get-article-comment/{id}";
  static final String DEL_ARTICLE_COMMENT = "/del-article-comment/{id}";
  static final String LIST_ARTICLE_COMMENT = "/list-article-comment";

  static final String CREATE_TYPE = "/create-type";
  static final String UPDATE_TYPE = "/update-type";
  static final String GET_TYPE = "/get-type/{id}";
  static final String DEL_TYPE = "/del-type/{id}";
  static final String LIST_TYPE = "/list-type";
  static final String LIST_ACTIVE_TYPE = "/list-active-type";
  static final String LIST_ALL ="/list-all" ;
  static final String UPDATE_TYPE_STATUS="/update-type-status";



}
