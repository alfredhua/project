package com.pro.admin.controller.website;

/**
 * @author hua
 * @date
 */
public class WebsiteUrl {

    static final  String WEBSITE_BASE_URL="/admin/website";
    static final String BLOG_BASE_URL = "/admin/blog";

    /**
     * 博客管理
     */
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

    /**
     * 博客类型
     */
    static final String CREATE_TYPE = "/create-type";
    static final String UPDATE_TYPE = "/update-type";
    static final String GET_TYPE = "/get-type/{id}";
    static final String DEL_TYPE = "/del-type/{id}";
    static final String LIST_TYPE = "/list-type";
    static final String LIST_ACTIVE_TYPE = "/list-active-type";
    static final String LIST_ALL ="/list-all" ;
    static final String UPDATE_TYPE_STATUS="/update-type-status";
    /**
     * 合伙人
     */
    static final String CREATE_PARTNER = "/create-partner";
    static final String UPDATE_PARTNER = "/update-partner";
    static final String GET_PARTNER =  "/get-partner/{id}";
    static final String DEL_PARTNER =  "/del-partner/{id}";
    static final String LIST_PARTNER =  "/list-partner";

    /**
     * 新闻管理
     */
    static final String CREATE_NEWS = "/create-news";
    static final String UPDATE_NEWS  = "/update-news";
    static final String UPDATE_NEWS_PUBLISH  = "/update-news-publish";
    static final String GET_NEWS =  "/get-news/{id}";
    static final String DEL_NEWS =  "/del-news/{id}";
    static final String LIST_NEWS =  "/list-news";


    /**
     * 产品相关
     */
    static final String CREATE_PRODUCE = "/create-produce";
    static final String UPDATE_PRODUCE = "/update-produce";
    static final String GET_PRODUCE = "/get-produce/{id}";
    static final String DEL_PRODUCE = "/del-produce/{id}";
    static final String LIST_PRODUCE = "list-produce";
    static final String UPDATE_PRODUCE_DETAIL = "/update-produce-detail";



    /**
     * 设置相关
     */
    static final String LIST_SETTING = "/list-setting";
    static final String UPDATE_SETTING_STATUS = "/update-setting-status";
    static final  String LIST_CHILDREN_SETTING="/list-children-setting/{type}";
    static final String UPDATE_SETTING_DETAIL = "/update-setting-detail";
    static final String GET_SETTING_DETAIL = "/get-setting-detail/{type}";

    /**
     * banner
     */
    static final String CREATE_BANNER = "/create-banner";
    static final String LIST_BANNER = "/list-banner";
    static final String UPDATE_BANNER = "/update-banner";
    static final String GET_BANNER = "/get-banner";
    static final String DELETE_BANNER = "/delete-banner/{id}";

    /**
     * 创建公告
     */
    static final String CREATE_NOTICE = "/create-notice";
    static final String UPDATE_NOTICE = "/update-notice";
    static final String DEL_NOTICE = "/del-notice/{id}";
    static final String UPDATE_PUBLISH_NOTICE = "/publish-notice/{id}";
    static final String GET_NOTICE = "/get-notice/{id}";
    static final String LIST_NOTICE = "/list-notice";


    /**
     * 公告字典类型
     */
    public static final String CREATE_NOTICE_TYPE="/create-notice-type";
    public static final String UPDATE_NOTICE_TYPE="/update-notice-type";
    public static final String UPDATE_NOTICE_TYPE_STATUS="/update-notice-type-status";
    public static final String LIST_ACTIVE_NOTICE_TYPE="/list-active-notice-type";
    public static final String LIST_ALL_NOTICE_TYPE="/list-all-notice-type";
    public static final String LIST_NOTICE_TYPE_BY_PAGE="/list-notice-type-by-page";


}
