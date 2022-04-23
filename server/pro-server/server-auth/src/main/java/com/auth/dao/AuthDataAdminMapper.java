package com.auth.dao;

import com.auth.entity.AuthDataAdmin;
import com.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2021/05/23
 */
@Mapper
public interface AuthDataAdminMapper extends BaseMapper<AuthDataAdmin> {

    @Select("select data_code from m_auth.auth_data_admin  where admin_id=#{adminId}")
    List<String> listByAdminId(@Param("adminId")  Long adminId);


    @Delete("delete from m_auth.auth_data_admin  where admin_id=#{adminId}")
    boolean deleteByAdminId(@Param("adminId")  Long adminId);

}
