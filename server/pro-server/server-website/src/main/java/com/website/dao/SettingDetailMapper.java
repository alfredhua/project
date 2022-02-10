package com.website.dao;

import com.pro.website.dto.entity.SettingDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
public interface SettingDetailMapper {

    String sql=" `id`,`type`,`content`,`url`,`introduce`,`create_time`,`update_time`,`del` ";

    @Select("select "+sql+" from m_website.site_setting_detail where type=#{type} and `del`=0 limit 1 ")
    SettingDetail getByType(@Param("type") String type);

    @Insert("insert into m_website.site_setting_detail(id,type,content,url,introduce,create_time)" +
            "values (#{id},#{type},#{content},#{url},#{introduce},now())")
    void createSettingDetail(SettingDetail settingDetailReqDTO);

    @UpdateProvider(type = SettingDetailProvider.class, method = "updateSettingDetail")
    boolean updateSettingDetail(SettingDetail settingDetailReqDTO);


}
