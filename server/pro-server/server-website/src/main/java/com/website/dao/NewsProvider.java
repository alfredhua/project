package com.website.dao;
import com.pro.website.dto.entity.News;
import com.pro.website.dto.NewsListReqDTO;
import org.apache.ibatis.jdbc.SQL;

/**
 * @auth guozhenhua
 * @date 2019/07/12
 */
public class NewsProvider {

    public String updateNews(News newsReqDTO) {
        return new SQL() {
            {
                UPDATE("m_website.site_news");
                if (newsReqDTO.getId() != null) {
                    SET("id = #{id}");
                }
                if (newsReqDTO.getTitle() != null) {
                    SET("title = #{title}");
                }
                if (newsReqDTO.getPublish() != null) {
                    SET("publish = #{publish}");
                }
                if (newsReqDTO.getSource() != null) {
                    SET("source = #{source}");
                }
                if (newsReqDTO.getSource_url() != null) {
                    SET("source_url = #{source_url}");
                }
                if (newsReqDTO.getPic() != null) {
                    SET("pic = #{pic}");
                }
                if (newsReqDTO.getIntroduce() != null) {
                    SET("introduce = #{introduce}");
                }
                if (newsReqDTO.getContext() != null) {
                    SET("context = #{context}");
                }
                if (newsReqDTO.getCreate_time() != null) {
                    SET("create_time = #{create_time}");
                }
                if (newsReqDTO.getUpdate_time() != null) {
                    SET("update_time = #{update_time}");
                }
                if (newsReqDTO.getAuth_data_code() != null) {
                    SET("auth_data_code = #{auth_data_code}");
                }
                if (newsReqDTO.getDel() != null) {
                    SET("del = #{del}");
                }
                WHERE("id= #{id}");
            }
        }.toString();
    }

    public String listNewsByPage(NewsListReqDTO newsListReqDTO) {
        return new SQL() {
            {
                SELECT("id,title,publish,source,source_url,pic,introduce,context,create_time,publish_time,update_time,del");
                FROM("m_website.site_news");
                WHERE("del=0");
                ORDER_BY("create_time desc limit "+newsListReqDTO.getOffset()
                             +" ,"+newsListReqDTO.getPage_size());
            }
        }.toString();
    }


    public String listNewsCount() {
        return new SQL() {
            {
                SELECT("ifnull(count(id),0)");
                FROM("m_website.site_news");
                WHERE("del=0");
                ORDER_BY("create_time desc ");
            }
        }.toString();
    }


    public String listNewsHome( ) {
        return new SQL() {
            {
                SELECT("id,title,publish,source,source_url,pic,introduce,context,create_time,publish_time,update_time,del");
                FROM("m_website.site_news");
                WHERE("del=0");
                WHERE("publish=1");
                ORDER_BY("create_time desc limit 6");
            }
        }.toString();
    }


    public String listNewsByPageForSite(NewsListReqDTO newsListReqDTO) {
        return new SQL() {
            {
                SELECT("id,title,publish,source,source_url,pic,introduce,context,create_time,publish_time,update_time,del");
                FROM("m_website.site_news");
                WHERE("del=0");
                WHERE("publish=1");
                ORDER_BY("create_time desc limit "+newsListReqDTO.getOffset()
                        +" ,"+newsListReqDTO.getPage_size());
            }
        }.toString();
    }

    public String listNewsCountForSite(NewsListReqDTO newsListReqDTO) {
        return new SQL() {
            {
                SELECT("ifnull(count(id),0)");
                FROM("m_website.site_news");
                WHERE("del=0");
                WHERE("publish=1");
                ORDER_BY("create_time desc limit "+newsListReqDTO.getOffset()+" ,"
                        +newsListReqDTO.getPage_size());
            }
        }.toString();
    }



}
