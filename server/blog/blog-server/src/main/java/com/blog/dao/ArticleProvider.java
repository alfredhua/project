package com.blog.dao;

import com.blog.dto.entity.Article;
import com.blog.dto.ArticleListReqDTO;
import org.apache.ibatis.jdbc.SQL;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
public class ArticleProvider {

    public String createArticle(Article articleReqDTO) {
        return new SQL() {
            {
                INSERT_INTO("m_blog.blog_article");
                if (articleReqDTO.getId() != null) {
                    VALUES("id", "#{id}");
                }
                if (articleReqDTO.getTitle() != null) {
                    VALUES("title", "#{title}");
                }
                if (articleReqDTO.getType() != null) {
                    VALUES("type", "#{type}");
                }
                if (articleReqDTO.getClick_count() != null) {
                    VALUES("click_count", "#{click_count}");
                }
                if (articleReqDTO.getContext() != null) {
                    VALUES("context", "#{context}");
                }
                if (articleReqDTO.getIntroduce()!=null){
                    VALUES("introduce", "#{introduce}");
                }
                if (articleReqDTO.getStatus() != null) {
                    VALUES("status", "#{status}");
                }
                VALUES("reprint", "#{reprint}");
                VALUES("content_type", "#{content_type}");
                if (articleReqDTO.getPic_url()!=null){
                    VALUES("pic_url", "#{pic_url}");
                }
                if (articleReqDTO.getCreate_time() != null) {
                    VALUES("create_time", "#{create_time}");
                }
                if (articleReqDTO.getUpdate_time() != null) {
                    VALUES("update_time", "#{update_time}");
                }
                if (articleReqDTO.getDel() != null) {
                    VALUES("del", "#{del}");
                }
            }
        }.toString();
    }

    public String updateArticle(Article articleReqDTO) {
        return new SQL() {
            {
                UPDATE("m_blog.blog_article");
                if (articleReqDTO.getTitle() != null) {
                    SET("title = #{title}");
                }
                if (articleReqDTO.getType() != null) {
                    SET("type = #{type}");
                }
                if (articleReqDTO.getClick_count() != null) {
                    SET("click_count = #{click_count}");
                }
                if (articleReqDTO.getContext() != null) {
                    SET("context = #{context}");
                }
                if (articleReqDTO.getIntroduce()!=null){
                    SET("introduce = #{introduce}");
                }
                SET("reprint=#{reprint}");
                SET("content_type=#{content_type}");
                if (articleReqDTO.getPic_url()!=null){
                    SET("pic_url= #{pic_url}");
                }
                if (articleReqDTO.getStatus() != null) {
                    SET("status = #{status}");
                }
                SET("update_time = now()");
                WHERE("id= #{id}");
            }
        }.toString();
    }

    public String listArticleByPage(ArticleListReqDTO articleListReqDTO) {
        return new SQL() {
            {
                SELECT("id,title,type,click_count,pic_url,introduce,like_count,reprint,content_type,context,status,create_time,update_time,del");
                FROM("m_blog.blog_article");
                WHERE( "del=0");
                if (articleListReqDTO.getStatus()!=null){
                    WHERE( "status=#{status}");
                }
                if (articleListReqDTO.getType()!=null&&!"all".equals(articleListReqDTO.getType())){
                    WHERE( "type=#{type}");
                }
                ORDER_BY("create_time desc limit "+articleListReqDTO.getOffset()+" ,"+articleListReqDTO.getPage_size());
            }
        }.toString();
    }


    public String listArticleCount(ArticleListReqDTO articleListReqDTO) {
        return new SQL() {
            {
                SELECT("ifnull(count(id),0)");
                FROM("m_blog.blog_article");
                WHERE( "del=0");
                WHERE( "status=1");
                if (articleListReqDTO.getType()!=null&&!"all".equals(articleListReqDTO.getType())){
                    WHERE( "type=#{type}");
                }
                ORDER_BY("create_time desc");
            }
        }.toString();
    }

}
