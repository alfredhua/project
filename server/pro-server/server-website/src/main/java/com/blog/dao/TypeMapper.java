package com.blog.dao;

import com.pro.blog.dto.TypeListReqDTO;
import com.pro.blog.dto.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
public interface TypeMapper {

    String sql=" `id`,`type`,`name`,`status`,`introduce`,`create_time`,`update_time`,`del` ";

    @Select("select "+sql+" from m_website.blog_type where id=#{id} and `del`=0 ")
    Type getById(@Param("id") long id);

    @Update("update m_website.blog_type set `del`=1 where id=#{id} and `del`=0 ")
    boolean delType(@Param("id") long id);

    @UpdateProvider(type = TypeProvider.class, method = "createType")
    void createType(Type typeReqDTO);

    @UpdateProvider(type = TypeProvider.class, method = "updateType")
    boolean updateType(Type typeReqDTO);

    @SelectProvider(type = TypeProvider.class, method = "listTypeByPage")
    List<Type> listTypeByPage(TypeListReqDTO typeListReqDTO);

    @SelectProvider(type = TypeProvider.class, method = "listTypeCount")
    Integer listTypeCount(TypeListReqDTO typeListReqDTO);

    @Select("select "+sql+" from m_website.blog_type where `del`=0")
    List<Type> listAll();

    @Select("select "+sql+" from m_website.blog_type where `del`=0 and status=1 ")
    List<Type> listAllActive();

    @Update("update  m_website.blog_type set status=#{status} where `del`=0 and id=#{id}")
    boolean updateTypeStatus(@Param("id") long id, @Param("status") int status);

    @Select("select "+sql+" from m_website.blog_type where type=#{type} and `del`=0 ")
    Type getByType(@Param("type") String type);

}
