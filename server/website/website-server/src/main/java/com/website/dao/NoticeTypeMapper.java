package com.website.dao;

import com.common.aspect.annotation.DataAuth;
import com.website.dto.entity.NoticeType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2018/12/20
 */
public interface NoticeTypeMapper {

    String sql=" id,type,name,create_time,update_time,`del`,status,auth_data_code ";

    @Select("select "+sql+" from m_website.dic_notice_type where `del`=0 order by create_time limit #{offset},#{pageSize}")
    @DataAuth
    List<NoticeType> listNoticeTypeByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("select count(id) from m_website.dic_notice_type where `del`=0")
    @DataAuth
    int listNoticeTypeByPageCount();

    @Insert("insert into m_website.dic_notice_type(id,type,name,auth_data_code)" +
            "values (#{id},#{type},#{name},#{auth_data_code})")
    void createNoticeType(NoticeType noticeTypeReqDTO);

    @Update("update m_website.dic_notice_type set name=#{name},auth_data_code=#{auth_data_code} where id=#{id} ")
    @DataAuth
    int updateNoticeTypeName(NoticeType noticeTypeReqDTO);

    @Update("update m_website.dic_notice_type set `status`=#{status} where id=#{id} and `status`!=#{status} and  del=0")
    @DataAuth
    int updateStatus(@Param("id") long id, @Param("status") short status);

    @Select("select "+sql+" from m_website.dic_notice_type where `del`=0 and status=1")
    @DataAuth
    List<NoticeType> listAllActive();

    @Select("select "+sql+" from m_website.dic_notice_type where `del`=0 ")
    List<NoticeType> listAll();

    @Select("select count(id) from m_website.dic_notice_type where `type`=#{type} and `del`=0")
    int getByType(@Param("type") String type);
}
