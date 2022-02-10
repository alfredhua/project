package com.website.service;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.PageUtil;
import com.website.dao.SettingMapper;
import com.pro.website.dto.SettingListReqDTO;
import com.pro.website.dto.SettingRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Component
public class SettingService{

    @Autowired
    SettingMapper settingMapper;

    public PageBean<SettingRespDTO> listSettingByPage(SettingListReqDTO settingListReqDTO) {
        PageBean<SettingRespDTO> pageBean = PageUtil.validatePage(settingListReqDTO.getPage_num(),
                settingListReqDTO.getPage_size(),settingListReqDTO.getOffset());
        List<SettingRespDTO> settingRespDTOS = settingMapper.listSettingByPage(settingListReqDTO);
        if (ObjectUtils.isEmpty(settingRespDTOS)){
            pageBean.setTotal(0);
            return pageBean;
        }
        for (SettingRespDTO settingRespDTO:settingRespDTOS) {
            settingRespDTO.setChildren(listChildren(settingRespDTO.getId()));
        }
        pageBean.setList(settingRespDTOS);
        pageBean.setTotal(settingMapper.listSettingCount(settingListReqDTO));
        return pageBean;
    }

    
    public void updateStatus(long id, short status) throws Exception {
        if(settingMapper.updateStatus(id,status)){
           return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }

    
    public List<SettingRespDTO> listSettingByPartnerId(long partnerId) {
        return settingMapper.listSettingByPartnerId(partnerId);
    }


    private List<SettingRespDTO> listChildren(long parent_id){
        List<SettingRespDTO> settingRespDTOS = settingMapper.listChildren(parent_id);
        if (ObjectUtils.isEmpty(settingRespDTOS)){
            return null;
        }else{
            for (SettingRespDTO settingRespDTO:settingRespDTOS) {
               settingRespDTO.setChildren( listChildren(settingRespDTO.getId()));
            }
        }
        return settingRespDTOS;
    }

     
     public SettingRespDTO getById(String id) {
         return settingMapper.getById(id);
     }


    public List<SettingRespDTO> listChildrenSetting(String type) {
        SettingRespDTO settingRespDTO=settingMapper.getByType(type);
        if (ObjectUtils.isEmpty(settingRespDTO)){
           return new ArrayList<>();
        }
        return settingMapper.listSettingByPartnerId(settingRespDTO.getId());
    }
}
