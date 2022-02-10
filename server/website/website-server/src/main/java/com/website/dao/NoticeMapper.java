package com.website.dao;

import com.common.dao.annotation.DataAuth;
import com.website.dto.entity.Notice;
import com.website.dto.NoticeListReqDTO;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2018/12/28
 */
public interface NoticeMapper {

    String sql=" `id`,`type`,`title`,`context`,`publish`,`create_time`,`update_time`,`del`,`ordering`,`click_count`,`publish_time`,auth_data_code ";

    @Select("select "+sql+" from m_website.site_notice where id=#{id} and `del`=0 ")
    @DataAuth
    Notice getById(@Param("id") long id);

    @Insert("insert into m_website.site_notice(id,type,title,context,publish,ordering,auth_data_code)" +
            "values (#{id},#{type},#{title},#{context},#{publish},#{ordering},#{auth_data_code})")
    void createNotice(Notice noticeReqDTO);

    @UpdateProvider(type = NoticeProvider.class, method = "updateNotice")
    @DataAuth
    boolean updateNotice(Notice noticeReqDTO);

    @Update("update m_website.site_notice set `del`=1 where id=#{id} and `del`=0 ")
    @DataAuth
    boolean delNotice(@Param("id") long id);

    @SelectProvider(type = NoticeProvider.class, method = "listNoticeByPage")
    @DataAuth
    List<Notice> listNoticeByPage(NoticeListReqDTO noticeListReqDTO);

    @SelectProvider(type = NoticeProvider.class, method = "listNoticeCount")
    @DataAuth
    Integer listNoticeCount(NoticeListReqDTO noticeListReqDTO);

    @Select("select count(id) from m_website.site_notice where `type`=#{type}  and `del`=0")
    @DataAuth
    Integer getCountByType(@Param("type")String type);

    @Update("update m_website.site_notice set publish=1,publish_time=now() where id=#{id} and publish=0")
    @DataAuth
    boolean updateNoticePublish(@Param("id") long id);
}
