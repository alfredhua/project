package com.auth.dao;
import com.auth.dto.entity.LoginLog;
import org.apache.ibatis.annotations.Insert;

public interface LoginLogMapper {

    @Insert("insert into m_auth.auth_login_log(id,admin_id,ip_address) values (#{id},#{adminId},#{ipAddress})")
    void insert(LoginLog loginLog);

}
