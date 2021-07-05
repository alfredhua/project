package com.website.dao;

import com.common.aspect.annotation.DataAuth;
import com.website.dto.entity.News;
import com.website.dto.NewsListReqDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/07/12
 */
public interface NewsMapper {

    String sql=" `id`,`title`,`publish`,`source`,`source_url`,`pic`,`introduce`,`context`,`create_time`,`update_time`,`del`,auth_data_code ";

    @Select("select "+sql+" from m_website.site_news where id=#{id} and `del`=0 ")
    News getById(@Param("id") String id);

    @Insert("insert into m_website.site_news(`id`,`title`,`source`,`source_url`,`pic`,`introduce`,`context`,`auth_data_code`)" +
            "values (#{id},#{title},#{source},#{source_url},#{pic},#{introduce},#{context},#{auth_data_code})")
    void createNews(News newsReqDTO);

    @UpdateProvider(type = NewsProvider.class, method = "updateNews")
    @DataAuth
    boolean updateNews(News newsReqDTO);

    @Update("update m_website.site_news set `del`=1 where id=#{id} and `del`=0 ")
    @DataAuth
    boolean delNews(@Param("id") String id);

    @SelectProvider(type = NewsProvider.class, method = "listNewsByPage")
    @DataAuth
    List<News> listNewsByPage(NewsListReqDTO newsListReqDTO);

    @SelectProvider(type = NewsProvider.class, method = "listNewsCount")
    @DataAuth
    Integer listNewsCount(NewsListReqDTO newsListReqDTO);

    @Update("update m_website.site_news set `publish`=#{publish}, publish_time=now() where id=#{id}")
    @DataAuth
    boolean updateNewsPublish(@Param("id") long id, @Param("publish") short publish);

    @Update("update m_website.site_news set `publish`=#{publish} where id=#{id}")
    @DataAuth
    boolean updateNewsPublish0(@Param("id") long id, @Param("publish") short publish);

    @SelectProvider(type = NewsProvider.class, method = "listNewsHome")
    @DataAuth
    List<News> listNewsHome();


    @SelectProvider(type = NewsProvider.class, method = "listNewsByPageForSite")
    @DataAuth
    List<News> listNewsByPageForSite(NewsListReqDTO newsListReqDTO);

    @SelectProvider(type = NewsProvider.class, method = "listNewsCountForSite")
    @DataAuth
    Integer listNewsCountForSite(NewsListReqDTO newsListReqDTO);
}
