package com.website.service;

import com.common.util.IDGenerateUtil;
import com.website.dao.SettingDetailMapper;
import com.website.entity.SettingDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Component
public class SettingDetailService {

    @Autowired
    SettingDetailMapper settingDetailMapper;


    public SettingDetail getByType(String type) {
        return settingDetailMapper.getByType(type);
    }


    public void updateSettingDetail(SettingDetail settingDetailReqDTO) throws Exception {
        SettingDetail settingDetailRespDTO = settingDetailMapper.getByType(settingDetailReqDTO.getType());
        if(ObjectUtils.isEmpty(settingDetailRespDTO)){
            settingDetailReqDTO.setId(IDGenerateUtil.generateId());
            settingDetailMapper.insert(settingDetailReqDTO);
            return;
        }
        settingDetailReqDTO.setId(settingDetailRespDTO.getId());
        settingDetailMapper.updateById(settingDetailReqDTO);
    }


}
