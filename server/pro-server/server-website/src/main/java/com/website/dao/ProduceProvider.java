package com.website.dao;
import com.pro.website.dto.entity.Produce;
import com.pro.website.dto.ProduceListReqDTO;
import org.apache.ibatis.jdbc.SQL;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
public class ProduceProvider {


    static final String sql_column=" id,title,file_list,pc_show,m_show,home_show,cover_image,ordering,create_time,update_time,del,introduce ";

    public String updateProduce(Produce produceReqDTO) {
        return new SQL() {
            {
                UPDATE("m_website.site_produce");
                if (produceReqDTO.getId() != null) {
                    SET("id = #{id}");
                }
                if (produceReqDTO.getTitle() != null) {
                    SET("title = #{title}");
                }
                if (produceReqDTO.getFile_list() != null) {
                    SET("file_list = #{file_list}");
                }
                if (produceReqDTO.getPc_show() != null) {
                    SET("pc_show = #{pc_show}");
                }
                if (produceReqDTO.getM_show() != null) {
                    SET("m_show = #{m_show}");
                }
                if (produceReqDTO.getCover_image() != null) {
                    SET("cover_image = #{cover_image}");
                }
                if (produceReqDTO.getHome_show() != null) {
                    SET("home_show = #{home_show}");
                }
                if (produceReqDTO.getOrdering() != null) {
                    SET("ordering = #{ordering}");
                }
                if (produceReqDTO.getCreate_time() != null) {
                    SET("create_time = #{create_time}");
                }
                if (produceReqDTO.getUpdate_time() != null) {
                    SET("update_time = #{update_time}");
                }
                if (produceReqDTO.getDel() != null) {
                    SET("del = #{del}");
                }
                if (produceReqDTO.getIntroduce() != null) {
                    SET("introduce = #{introduce}");
                }
                if (produceReqDTO.getContext() != null) {
                    SET("context = #{context}");
                }
                WHERE("id= #{id}");
            }
        }.toString();
    }

    public String listProduceByPage(ProduceListReqDTO produceListReqDTO) {
        return new SQL() {
            {
                SELECT(sql_column);
                FROM("m_website.site_produce");
                WHERE("del=0");
                ORDER_BY("create_time desc limit "+produceListReqDTO.getOffset()
                        +" ,"+produceListReqDTO.getPage_size());
            }
        }.toString();
    }

    public String listProduceHome() {
        return new SQL() {
            {
                SELECT(sql_column);
                FROM("m_website.site_produce");
                WHERE("del=0");
                WHERE("home_show=1");
                ORDER_BY("ordering,create_time desc limit 4");
            }
        }.toString();
    }


    public String listAllProduce() {
        return new SQL() {
            {
                SELECT(sql_column);
                FROM("m_website.site_produce");
                WHERE("del=0");
                WHERE("home_show=1");
                ORDER_BY("ordering,create_time desc");
            }
        }.toString();
    }

    public String listProduceCount(ProduceListReqDTO produceListReqDTO) {
        return new SQL() {
            {
                SELECT("ifnull(count(id),0)");
                FROM("m_website.site_produce");
                WHERE("del=0");
                ORDER_BY("create_time desc limit "+produceListReqDTO.getOffset()+" ,"
                        +produceListReqDTO.getPage_size());
            }
        }.toString();
    }

}
