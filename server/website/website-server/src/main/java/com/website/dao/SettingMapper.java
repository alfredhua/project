package com.website.dao;

import com.common.dao.annotation.DataAuth;
import com.website.dto.SettingListReqDTO;
import com.website.dto.SettingRespDTO;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
public interface SettingMapper {

    String sql=" `id`,`type`,`name`,`status`,`partner_id`,`create_time`,`update_time`,`del` ";

    @Select("select "+sql+" from m_website.site_setting where id=#{id} and `del`=0 ")
    @DataAuth
    SettingRespDTO getById(@Param("id") String id);

    @Select("select "+sql+" from m_website.site_setting where type=#{type} and `del`=0 ")
    @DataAuth
    SettingRespDTO getByType(@Param("type")String type);

    @Select("select "+sql+" from m_website.site_setting where `del`=0 and partner_id=0  limit #{offset},#{page_size}")
    @DataAuth
    List<SettingRespDTO> listSettingByPage(SettingListReqDTO settingListReqDTO);


    @Select("select "+sql+" from m_website.site_setting where `del`=0 and partner_id=#{partner_id} ")
    @DataAuth
    List<SettingRespDTO> listChildren(@Param("partner_id") long partner_id);

    @Select("select "+sql+" from m_website.site_setting where status=1 and `del`=0 and partner_id=#{partner_id}  order by ordering,create_time ")
    @DataAuth
    List<SettingRespDTO> listSettingByPartnerId(@Param("partner_id") long partner_id);

    @SelectProvider(type = SettingProvider.class, method = "listSettingCount")
    @DataAuth
    Integer listSettingCount(SettingListReqDTO settingListReqDTO);

    @Update("update m_website.site_setting set status=#{status} where id=#{id}")
    @DataAuth
    boolean updateStatus(@Param("id") long id, @Param("status") short status);

}
