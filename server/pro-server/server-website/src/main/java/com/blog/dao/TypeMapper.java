package com.blog.dao;

import com.blog.entity.Type;
import com.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
@Mapper
public interface TypeMapper extends BaseMapper<Type> {

    String sql=" `id`,`type`,`name`,`status`,`introduce`,`create_time`,`update_time`,`del` ";

    @Select("select "+sql+" from m_website.blog_type where type=#{type} and `del`=0 ")
    Type getByType(@Param("type") String type);

}
