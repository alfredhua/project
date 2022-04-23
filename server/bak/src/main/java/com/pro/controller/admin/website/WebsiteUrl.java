package com.pro.controller.admin.website;

/**
 * @author hua
 * @date
 */
public class WebsiteUrl {



   /**
    *
    */
  static final  String BASE_URL="/admin/website";

   /**
    * 合伙人
    */
   static final String CREATE_PARTNER = "/create-partner";
   static final String UPDATE_PARTNER = "/update-partner";
   static final String GET_PARTNER =  "/get-partner/{id}";
   static final String DEL_PARTNER =  "/del-partner/{id}";
   static final String LIST_PARTNER =  "/list-partner";


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

 /**
    * 设置相关
    */
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
