package com.website.dao;
import com.website.dto.entity.Setting;
import com.website.dto.SettingListReqDTO;
import org.apache.ibatis.jdbc.SQL;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
public class SettingProvider {

    public String updateSetting(Setting settingReqDTO) {
        return new SQL() {
            {
                UPDATE("m_website.site_setting");
                if (settingReqDTO.getId() != null) {
                    SET("id = #{id}");
                }
                    if (settingReqDTO.getType() != null) {
                    SET("type = #{type}");
                }
                    if (settingReqDTO.getName() != null) {
                    SET("name = #{name}");
                }
                    if (settingReqDTO.getStatus() != null) {
                    SET("status = #{status}");
                }
                    if (settingReqDTO.getPartner_id() != null) {
                    SET("partner_id = #{partner_id}");
                }
                    if (settingReqDTO.getCreate_time() != null) {
                    SET("create_time = #{create_time}");
                }
                    if (settingReqDTO.getUpdate_time() != null) {
                    SET("update_time = #{update_time}");
                }
                    if (settingReqDTO.getDel() != null) {
                    SET("del = #{del}");
                }
                                WHERE("id= #{id}");
            }
        }.toString();
    }


    public String listSettingCount(SettingListReqDTO settingListReqDTO) {
        return new SQL() {
            {
                SELECT("ifnull(count(id),0)");
                FROM("m_website.site_setting");
                ORDER_BY("create_time desc limit "+settingListReqDTO.getOffset()+" ,"
                         +settingListReqDTO.getPage_size());
            }
        }.toString();
    }

}
