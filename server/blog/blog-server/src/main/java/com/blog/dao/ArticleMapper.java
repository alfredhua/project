package com.blog.dao;

import com.blog.dto.entity.Article;
import com.blog.dto.ArticleListReqDTO;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
public interface ArticleMapper {

    String sql=" `id`,`title`,`pic_url`,`reprint`,`type`,`introduce`,`content_type`,`click_count`,`context`,`status`,`create_time`,`update_time`,`del` ";

    @Select("select "+sql+" from m_blog.blog_article where id=#{id} and `del`=0 ")
    Article getById(@Param("id") long id);

    @Update("update m_blog.blog_article set `del`=1 where id=#{id} and `del`=0 ")
    boolean delArticle(@Param("id") long id);

    @UpdateProvider(type = ArticleProvider.class, method = "createArticle")
    void createArticle(Article article);

    @UpdateProvider(type = ArticleProvider.class, method = "updateArticle")
    boolean updateArticle(Article articleReqDTO);

    @SelectProvider(type = ArticleProvider.class, method = "listArticleByPage")
    List<Article> listArticleByPage(ArticleListReqDTO articleListReqDTO);

    @SelectProvider(type = ArticleProvider.class, method = "listArticleCount")
    Integer listArticleCount(ArticleListReqDTO articleListReqDTO);

    @Select("<script>" +
            "select "+sql+" from m_blog.blog_article where status=1 and `del`=0 " +
            "<if test=\"type!=null\"> and type=#{type} </if>"+
            " order by click_count desc limit 5"+
            "</script>")
    List<Article> listArticleByCharts(@Param("type") String type);

    @Select("select "+sql+" from m_blog.blog_article where `del`=0 and status=1 order by create_time desc limit 10  ")
    List<Article>  listArticleHome();

    @Update("update m_blog.blog_article set click_count=click_count+1 where id=#{id} and `del`=0 ")
    Boolean updateClickCount(@Param("id") long id);

    @Update("update m_blog.blog_article set status=#{status} where id=#{id} and `del`=0 ")
    Boolean updateArticleStatus(@Param("id") long id, @Param("status") short status);

}
