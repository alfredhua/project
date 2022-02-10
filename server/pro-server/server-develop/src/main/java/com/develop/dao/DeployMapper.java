package com.develop.dao;

import com.pro.develop.dto.entity.Deploy;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @auth guozhenhua
 * @date 2021/01/17
 */
public interface DeployMapper   {

    String sql=" id,name,name_value,description,operator,create_time,update_time,del ";

    @Select("select "+sql+" from m_develop.de_deploy where name=#{name}")
    Deploy getByName(@Param("name")String name);

    @Insert("insert into m_develop.de_deploy(id,name,name_value,description,operator,create_time,update_time,del)" +
            "values(#{id},#{name},#{name_value},#{description},#{operator},#{create_time},#{update_time},0)")
    void insert(Deploy deploy);

    @Update("update m_develop.de_deploy set name_value=#{name_value},description=#{description},operator=#{operator},update_time=#{update_time} where name=#{name}")
    int updateByName(Deploy deploy);

    @Update("update m_develop.de_deploy set del=1 where name=#{name}")
    int delDevelop(@Param("name")String name,@Param("operator") String operator);
}
