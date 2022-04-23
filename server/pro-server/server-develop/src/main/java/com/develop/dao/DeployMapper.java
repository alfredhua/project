package com.develop.dao;

import com.common.mybatis.BaseMapper;
import com.develop.entity.Deploy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @auth guozhenhua
 * @date 2021/01/17
 */
@Mapper
public interface DeployMapper  extends BaseMapper<Deploy> {

    String sql=" id,name,name_value,description,operator,create_time,update_time,del ";

    @Select("select "+sql+" from m_develop.de_deploy where name=#{name}")
    Deploy getByName(@Param("name")String name);

    @Update("update m_develop.de_deploy set name_value=#{name_value},description=#{description},operator=#{operator},update_time=#{update_time} where name=#{name}")
    int updateByName(Deploy deploy);

    @Update("update m_develop.de_deploy set del=1 where name=#{name}")
    int deleteByName(@Param("name")String name,@Param("operator") String operator);
}
