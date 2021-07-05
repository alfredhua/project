package com.message.dao;

import com.message.dto.entity.SmsRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @auth guozhenhua
 * @date 2018/11/16
 */
@Mapper
public interface SmsRecordMapper{


    @Insert("insert into m_message.sms_records (id,numbers,content,status,template_code,channel_type) values (#{id},#{numbers}, #{content}, #{status}, #{template_code},#{channel_type})")
    void insert(SmsRecord smsRecord);

    @Update("update m_message.sms_records set status=#{status},result = #{result},error_msg=#{error_msg} where id = #{id}")
    boolean updateSmsRecordById(@Param("id") long id, @Param("status") short status, @Param("result") String result,@Param("error_msg")String errorMsg);


}
