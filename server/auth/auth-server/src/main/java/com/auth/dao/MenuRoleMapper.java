package com.auth.dao;

import com.auth.dto.entity.MenuRole;
import com.common.aspect.annotation.DataAuth;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author hua
 */
public interface MenuRoleMapper {

    String sql=" id,name,auth_list,comment,create_time,update_time,status ";

    @Insert("insert into m_auth.auth_role(id,name,auth_list,comment) value (#{id},#{name},#{auth_list},#{comment})")
    void createRole(MenuRole menuRole);

    @Select("select "+sql+" from m_auth.auth_role limit #{offset},#{pageSize}")
    @DataAuth
    List<MenuRole> listRole(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("select count(*) from m_auth.auth_role ")
    int listRoleCount();

    @Select("select "+sql+" from m_auth.auth_role where id=#{id}")
    MenuRole getRoleById(@Param("id") long id);

    @Update("update m_auth.auth_role set name=#{name}," +
            "auth_list=#{auth_list},comment=#{comment},update_time=now() where  id=#{id}")
    boolean updateRole(MenuRole menuRoleReqDTO);

    @Select("select "+sql+" from m_auth.auth_role where status=0")
    @DataAuth
    List<MenuRole> listAllUseRole();

    @Select("<script>" +
            "select auth_list from m_auth.auth_role where id in  "+
               "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
                "#{item}"+
               "</foreach>"+
            "</script>")
    List<String> getRoleByIdList(@Param("ids") List<Object> ids);

    @Update("update  m_auth.auth_role set status=true where id=${id} and status=false")
    boolean updateActiveStatus(@Param("id") long id);

    @Update("update  m_auth.auth_role set status=false where id=${id} and status=true")
    boolean updateUnActiveStatus(@Param("id") long id);

}
