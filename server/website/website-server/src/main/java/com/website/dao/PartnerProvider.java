package com.website.dao;
import com.website.dto.entity.Partner;
import org.apache.ibatis.jdbc.SQL;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
public class PartnerProvider {

    public String updatePartner(Partner partnerReqDTO) {
        return new SQL() {
            {
                UPDATE("m_website.site_partner");
                if (partnerReqDTO.getId() != null) {
                    SET("id = #{id}");
                }
                if (partnerReqDTO.getName() != null) {
                    SET("name = #{name}");
                }
                if (partnerReqDTO.getHref() != null) {
                    SET("href = #{href}");
                }
                if (partnerReqDTO.getPic_url() != null) {
                    SET("pic_url = #{pic_url}");
                }
                if (partnerReqDTO.getDel() != null) {
                    SET("del = #{del}");
                }
                if (partnerReqDTO.getCreate_time() != null) {
                    SET("create_time = #{create_time}");
                }
                if (partnerReqDTO.getUpdate_time() != null) {
                    SET("update_time = #{update_time}");
                }
                WHERE("id= #{id}");
            }
        }.toString();
    }

    public String listPartnerByPage(Integer offset,Integer page_size) {
        return new SQL() {
            {
                SELECT("id,name,href,pic_url,del,create_time,update_time");
                FROM("m_website.site_partner");
                WHERE("del=0");
                ORDER_BY(" create_time desc limit "+offset+" ,"+page_size);
            }
        }.toString();
    }


    public String listPartnerCount() {
        return new SQL() {
            {
                SELECT("ifnull(count(id),0)");
                FROM("m_website.site_partner");
                WHERE("del=0");
                ORDER_BY("create_time desc ");
            }
        }.toString();
    }



    public String listAllPartner() {
        return new SQL() {
            {
                SELECT("id,name,href,pic_url,del,create_time,update_time");
                FROM("m_website.site_partner");
                WHERE("del=0");
                ORDER_BY(" create_time desc ");
            }
        }.toString();
    }



}
