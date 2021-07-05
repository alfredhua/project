package com.website.dao;

import com.website.dto.entity.SettingDetail;
import org.apache.ibatis.jdbc.SQL;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
public class SettingDetailProvider {


    public String updateSettingDetail(SettingDetail settingDetailReqDTO) {
        return new SQL() {
            {
                UPDATE("m_website.site_setting_detail");
                if (settingDetailReqDTO.getType() != null) {
                    SET("type = #{type}");
                }
                if (settingDetailReqDTO.getContent() != null) {
                    SET("content = #{content}");
                }
                if (settingDetailReqDTO.getIntroduce() != null) {
                    SET("introduce = #{introduce}");
                }
                if (settingDetailReqDTO.getUrl() != null) {
                    SET("url = #{url}");
                }
                if (settingDetailReqDTO.getContent() != null) {
                    SET("content = #{content}");
                }
                SET("update_time = now()");
                if (settingDetailReqDTO.getDel() != null) {
                    SET("del = #{del}");
                }
                WHERE("id= #{id}");
            }
        }.toString();
    }

}
