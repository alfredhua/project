package com.website.service;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.util.IDGenerate;
import com.website.dao.SettingDetailMapper;
import com.website.dto.entity.SettingDetail;
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
            settingDetailReqDTO.setId(IDGenerate.generateId());
            settingDetailMapper.createSettingDetail(settingDetailReqDTO);
            return;
        }
        settingDetailReqDTO.setId(settingDetailRespDTO.getId());
        if(settingDetailMapper.updateSettingDetail(settingDetailReqDTO)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }


}
