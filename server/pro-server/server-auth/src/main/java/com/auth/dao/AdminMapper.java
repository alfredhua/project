package com.auth.dao;

import com.common.mybatis.BaseMapper;
import com.pro.auth.dto.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author hua
 */
public interface AdminMapper extends BaseMapper<Admin> {

    String sql=" id,user_name,role_id_list,phone,email,password,status,update_time,create_time,auth_data_code ";

    @Select("select "+sql+" from m_auth.auth_admin where phone=#{phone}")
    Admin getAdminByPhone(@Param("phone") String phone);

    @Select("select "+sql+" from m_auth.auth_admin where email=#{email}")
    Admin getAdminByEmail(@Param("email") String email);

    @Select("select "+sql+"  from m_auth.auth_admin where user_name=#{user_name}")
    Admin getAdminByUserName(@Param("user_name") String user_name);

    @Update("update m_auth.auth_admin set status = true where id=#{id} and status=false ")
    boolean updateFrozenAdmin(@Param("id") String id);

    @Update("update m_auth.auth_admin set status = false where id=#{id} and status=true ")
    boolean updateUnFrozenAdmin(@Param("id") String id);

    @Select("<script>" +
             "select count(id) from  m_auth.auth_admin where email=#{email} " +
             "<if test=\" id!=null and id!=''\"> and id !=#{id} </if>" +
            "</script>")
    int validateEmail(@Param("email") String email, @Param("id") Long id);

    @Select("<script>" +
                "select count(id) from  m_auth.auth_admin where phone=#{phone}"+
                "<if test=\" id!=null and id!=''\"> and id !=#{id} </if>" +
           "</script>")
    int validatePhone(@Param("phone") String phone, @Param("id") Long id);

    @Select("<script>" +
                "select count(id) from  m_auth.auth_admin where user_name=#{user_name} " +
                "<if test=\" id!=null and id!=''\"> and id !=#{id} </if>" +
            "</script>")
    int validateUserName(@Param("user_name") String user_name, @Param("id") Long id);

    @Update("update m_auth.auth_admin set password = #{password},update_password=0 where id=#{id}")
    boolean resetAdminPassword(@Param("id")long id,@Param("password") String password);

}
