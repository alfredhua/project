package com.message.dao;

import com.common.mybatis.BaseMapper;
import com.message.dao.entity.WeChatUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeChatUserInfoMapper extends BaseMapper<WeChatUserInfo> {

//    String cloumns=" id,user_id,nickname,openid,headimgurl,sex,province,city,country,unionid,create_time,update_time,del ";
//
//    @Select("select count(id) from m_message.wx_user_info where openid=#{openid} ")
//    int exist(@Param("openid") String openid);
//
//    @Select("select "+cloumns+" from m_message.wx_user_info where openid=#{openid} ")
//    WeChatUserInfo getByOpenid(String openid);
//
//    @Select("select count(id) from m_message.wx_user_info where openid=#{openid} ")
//    int getCountByOpenid(String openid);
//
//    @Update("update m_message.wx_user_info set nickname=#{nickname},headimgurl=#{headimgurl},sex=#{sex},province=#{province},city=#{city},country=#{country},unionid=#{unionid}," +
//            "update_time=now() where openid=${openid}")
//    void update(WeChatUserInfo weChatUserInfo);
//
//
//    @Insert("insert into m_message.wx_user_info("+cloumns+")" +
//            " values(#{id},#{user_id},#{nickname},#{openid},#{headimgurl},#{sex},#{province},#{city},#{country},#{unionid},now())")
//    void insert(WeChatUserInfo weChatUserInfo);
//
//    @Select("select "+cloumns+" from m_message.wx_user_info where user_id=#{user_id} ")
//    WeChatUserInfo getByUserId(@Param("userid") long userid);

}
