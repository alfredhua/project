package com.blog.dao;

import com.blog.dto.TypeListReqDTO;
import com.blog.dto.entity.Type;
import org.apache.ibatis.jdbc.SQL;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
public class TypeProvider {

    public String createType(Type type) {
        return new SQL() {
            {
                INSERT_INTO("m_website.blog_type");
                if (type.getId() != null) {
                    VALUES("id", "#{id}");
                }
                if (type.getType() != null) {
                    VALUES("type", "#{type}");
                }
                if (type.getName() != null) {
                    VALUES("name", "#{name}");
                }
                if (type.getIntroduce()!=null){
                    VALUES("introduce", "#{introduce}");
                }
                if (type.getStatus() != null) {
                    VALUES("status", "#{status}");
                }
                if (type.getCreate_time() != null) {
                    VALUES("create_time", "#{create_time}");
                }
                if (type.getUpdate_time() != null) {
                    VALUES("update_time", "#{update_time}");
                }
                if (type.getDel() != null) {
                    VALUES("del", "#{del}");
                }
            }
        }.toString();
    }

    public String updateType(Type type) {
        return new SQL() {
            {
                UPDATE("m_website.blog_type");
                if (type.getId() != null) {
                    SET("id = #{id}");
                }
                if (type.getType() != null) {
                    SET("type = #{type}");
                }
                if (type.getName() != null) {
                    SET("name = #{name}");
                }
                if (type.getIntroduce()!=null){
                    SET("introduce = #{introduce}");
                }
                if (type.getStatus() != null) {
                    SET("status = #{status}");
                }
                if (type.getCreate_time() != null) {
                    SET("create_time = #{create_time}");
                }
                if (type.getUpdate_time() != null) {
                    SET("update_time = #{update_time}");
                }
                if (type.getDel() != null) {
                    SET("del = #{del}");
                }
                WHERE("id= #{id}");
            }
        }.toString();
    }

    public String listTypeByPage(TypeListReqDTO typeListReqDTO) {
        return new SQL() {
            {
                SELECT("id,type,name,status,introduce,create_time,update_time,del");
                FROM("m_website.blog_type");
                WHERE("del = 0");
                WHERE("status = 1");
                if (typeListReqDTO.getType() != null) {
                    WHERE("type = #{type}");
                }
                ORDER_BY("create_time desc limit "+typeListReqDTO.getOffset()+" ,"+typeListReqDTO.getPage_size());
            }
        }.toString();
    }

    public String listTypeCount(TypeListReqDTO typeListReqDTO) {
        return new SQL() {
            {
                SELECT("ifnull(count(id),0)");
                FROM("m_website.blog_type");
                WHERE("del = 0");
                WHERE("status = 1");
                if (typeListReqDTO.getType() != null) {
                    WHERE("type = #{type}");
                }
                ORDER_BY("create_time desc");
            }
        }.toString();
    }

}
