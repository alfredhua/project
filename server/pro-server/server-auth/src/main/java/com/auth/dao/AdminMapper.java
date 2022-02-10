package com.auth.dao;

import com.pro.auth.dto.entity.Admin;
import com.pro.auth.dto.AdminListReqDTO;
import com.common.aspect.annotation.DataAuth;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * @author hua
 */
public interface AdminMapper {

    String sql=" id,user_name,role_id_list,phone,email,password,status,update_time,create_time,auth_data_code ";

    @Insert("insert into m_auth.auth_admin(id,user_name,role_id_list,phone,email,password,status) " +
            " values(#{id},#{user_name},#{role_id_list},#{phone},#{email},#{password},#{status}) ")
    void insert(Admin admin);

    @Update("update m_auth.auth_admin set user_name=#{user_name},role_id_list=#{role_id_list}," +
            "phone=#{phone},email=#{email},status=#{status} where id=#{id}")
    boolean updateAdmin(Admin admin);

    @Select("select "+sql+" from m_auth.auth_admin where phone=#{phone}")
    Admin getAdminByPhone(@Param("phone") String phone);

    @Select("select "+sql+" from m_auth.auth_admin where email=#{email}")
    Admin getAdminByEmail(@Param("email") String email);

    @Select("select "+sql+"  from m_auth.auth_admin where user_name=#{user_name}")
    Admin getAdminByUserName(@Param("user_name") String user_name);

    @Select("<script>" +
            "select auth_admin.id,auth_admin.user_name,auth_admin.role_id_list,auth_admin.phone,auth_admin.email,auth_admin.password,auth_admin.status,auth_admin.update_time,auth_admin.create_time from m_auth.auth_data_admin as auth_data_admin" +
            " left join m_auth.auth_admin as auth_admin  on auth_data_admin.admin_id=auth_admin.id  " +
            "where auth_data_admin.del=0 "+
            "<if test=\" phone!=null and  phone!='' \"> and auth_admin.phone=#{phone} </if>" +
            "<if test=\" user_name!=null and  user_name!='' \"> and auth_admin.user_name=#{user_name} </if>" +
            " order by create_time desc limit #{offset},#{page_size}" +
            "</script>")
    @DataAuth
    List<Admin> listAdminByPage(AdminListReqDTO adminListReqDTO);

    @Select("<script>" +
            "select count(id) from m_auth.auth_admin where 1=1  " +
            "<if test=\" phone!=null and  phone!='' \"> and phone=#{phone} </if>" +
            "<if test=\" user_name!=null and user_name!='' \"> and user_name=#{user_name} </if> " +
            " order by create_time desc " +
            "</script>")
    @DataAuth
    int listAdminCount(AdminListReqDTO adminListReqDTO);

    @Update("update m_auth.auth_admin set status = true where id=#{id} and  status=false ")
    boolean updateFrozenAdmin(@Param("id") String id);

    @Update("update m_auth.auth_admin set status = false where id=#{id} and  status=true ")
    boolean updateUnFrozenAdmin(@Param("id") String id);

    @Select("select "+sql+" from  m_auth.auth_admin where id=#{id}")
    Admin getAdminById(@Param("id") long id);

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

    @Update("update m_auth.auth_admin set phone = #{phone},email=#{email} where id=#{id}")
    boolean updateAdminInfo(@Param("id")long id,@Param("phone") String phone,@Param("email") String email);

    @Update("update m_auth.auth_admin set auth_data_code = #{auth_data_code} where id=#{id}")
    boolean setAuthData(@Param("id")long id,@Param("auth_data_code") String auth_data_code);

}
