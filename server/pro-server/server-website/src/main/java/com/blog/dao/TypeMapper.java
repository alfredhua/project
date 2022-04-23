package com.blog.dao;

import com.common.mybatis.BaseMapper;
import com.pro.blog.dto.TypeListReqDTO;
import com.pro.blog.dto.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
public interface TypeMapper extends BaseMapper<Type> {

    String sql=" `id`,`type`,`name`,`status`,`introduce`,`create_time`,`update_time`,`del` ";

    @Select("select "+sql+" from m_website.blog_type where type=#{type} and `del`=0 ")
    Type getByType(@Param("type") String type);

}
