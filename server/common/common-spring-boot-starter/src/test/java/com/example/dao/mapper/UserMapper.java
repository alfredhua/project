package com.example.dao.mapper;

import com.common.dao.BaseMapper;
import com.example.dao.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
