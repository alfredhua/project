package com.website.dao;

import com.common.aspect.annotation.DataAuth;
import com.pro.website.dto.entity.Banner;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author hua
 */
@Mapper
public interface BannerMapper{


    String sql=" id,url,name,href,enable,del,`order`,`type`,create_time,update_time,auth_data_code ";

    @Insert("insert into m_website.site_banner(`id`,`url`,`name`,`href`,`enable`,`order`,`type`,`auth_data_code`)" +
            " values (#{id},#{url},#{name},#{href},#{enable},#{order},#{type},#{auth_data_code}) ")
    void createBanner(Banner bannerReqDTO);

    @Select("select "+sql+" from m_website.site_banner where del=false order by `order` desc,create_time desc limit #{offset},#{pageSize}")
    @DataAuth
    List<Banner> listBanners(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(id) from m_website.site_banner where del=false")
    @DataAuth
    int listBannerCount();

    @Update("update m_website.site_banner set `url`=#{url},`name`=#{name},`href`=#{href},`enable`=#{enable}," +
            "`order`=#{order},`type`=#{type},auth_data_code=#{auth_data_code} where id=#{id}")
    @DataAuth
    boolean updateBanner(Banner bannerReqDTO);

    @Select("select "+sql+" from m_website.site_banner where id=#{id}")
    @DataAuth
    Banner getBannerById(@Param("id") long id);

    @Select("select "+sql+" from m_website.site_banner where type=#{type} and enable=1 and del=0 order by `order` desc, create_time desc")
    @DataAuth
    List<Banner> listBannersByType(String type);

    @Update("update m_website.site_banner set del=true where id=#{id}")
    @DataAuth
    boolean delBanner(@Param("id") long id);

}
