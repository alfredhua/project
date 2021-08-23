package com.website.dao;
import com.website.dto.entity.Navigate;
import com.website.dto.NavigateListReqDTO;
import org.apache.ibatis.jdbc.SQL;

/**
 * @auth guozhenhua
 * @date 2021/08/23
 */
public class NavigateProvider {

    public String createNavigate(Navigate navigate) {
        return new SQL() {
            {
                INSERT_INTO("m_website.site_navigate");
                if (navigate.getId() != null) {
                    VALUES("id", "id");
                }
                if (navigate.getType() != null) {
                    VALUES("type", "type");
                }
                if (navigate.getTitle() != null) {
                    VALUES("title", "title");
                }
                if (navigate.getIcon() != null) {
                    VALUES("icon", "icon");
                }
                if (navigate.getIntroduce() != null) {
                    VALUES("introduce", "introduce");
                }
                if (navigate.getCreate_time() != null) {
                    VALUES("create_time", "create_time");
                }
                if (navigate.getUpdate_time() != null) {
                    VALUES("update_time", "update_time");
                }
                if (navigate.getDel() != null) {
                    VALUES("del", "del");
                }
            }
        }.toString();
    }

    public String updateNavigate(Navigate navigate) {
        return new SQL() {
            {
                UPDATE("m_website.site_navigate");
                if (navigate.getId() != null) {
                    SET("id = #{id}");
                }
                if (navigate.getType() != null) {
                    SET("type = #{type}");
                }
                if (navigate.getTitle() != null) {
                    SET("title = #{title}");
                }
                if (navigate.getIcon() != null) {
                    SET("icon = #{icon}");
                }
                if (navigate.getIntroduce() != null) {
                    SET("introduce = #{introduce}");
                }
                if (navigate.getCreate_time() != null) {
                    SET("create_time = #{create_time}");
                }
                if (navigate.getUpdate_time() != null) {
                    SET("update_time = #{update_time}");
                }
                if (navigate.getDel() != null) {
                    SET("del = #{del}");
                }
                WHERE("id= #{id}");
            }
        }.toString();
    }

    public String listNavigateByPage(NavigateListReqDTO navigateListReqDTO) {
        return new SQL() {
            {
                SELECT("id,type,title,icon,introduce,create_time,update_time,del");
                FROM("m_website.site_navigate");
                WHERE("del=0");
                ORDER_BY("create_time desc limit "+navigateListReqDTO.getOffset()+" ,"+navigateListReqDTO.getPage_size());
            }
        }.toString();
    }

    public String listNavigateCount(NavigateListReqDTO navigateListReqDTO) {
        return new SQL() {
            {
                SELECT("ifnull(count(id),0)");
                FROM("m_website.site_navigate");
                WHERE("del=0");
                ORDER_BY("create_time desc");
            }
        }.toString();
    }

}
