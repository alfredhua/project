package com.auth.dao;

import com.auth.dto.entity.AuthData;
import com.common.aspect.annotation.DataAuth;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2021/05/23
 */
public interface AuthDataMapper {

    @Select("select * from m_auth.auth_data where del=0 order by create_time desc limit #{offset},#{pageSize}")
    @DataAuth(value = "code")
    List<AuthData> listAuthDataByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("select count(*) from m_auth.auth_data where del=0")
    @DataAuth(value = "code")
    int listAuthDataCount();

    @Insert("insert into m_auth.auth_data(id,code,name)values(#{id},#{code},#{name})")
    void saveAuthData(AuthData authData);

    @Update("update m_auth.auth_data set name=#{name} where id=#{id}")
    int updateAuthData(AuthData authData);

    @Select("select * from m_auth.auth_data where del=0 order by create_time desc")
    @DataAuth(value = "code")
    List<AuthData> listAuthDataAll();


}
