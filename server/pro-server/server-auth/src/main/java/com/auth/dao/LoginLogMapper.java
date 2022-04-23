package com.auth.dao;

import com.auth.entity.LoginLog;
import com.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {

}
