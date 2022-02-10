package com.auth.dao;

import com.pro.auth.dto.entity.AuthDataAdmin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2021/05/23
 */
public interface AuthDataAdminMapper {

    @Insert("insert into m_auth.auth_data_admin(id,admin_id,data_code)values(#{id},#{admin_id},#{data_code})")
    void saveAuthDataAdmin(AuthDataAdmin authDataAdmin);

    @Update("update  m_auth.auth_data_admin set del=1 where admin_id=#{adminId}")
    void deleteByAdminId(@Param("adminId") Long adminId);

    @Select("select data_code from  m_auth.auth_data_admin  where admin_id=#{adminId}")
    List<String> listByAdminId(@Param("adminId")  Long adminId);
}
