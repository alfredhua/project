package com.website.dao;

import com.common.mybatis.BaseMapper;
import com.website.entity.SettingDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */

@Mapper
public interface SettingDetailMapper extends BaseMapper<SettingDetail> {

    @Select("select * from m_website.site_setting_detail where type=#{type}")
    SettingDetail getByType(String type);
}
