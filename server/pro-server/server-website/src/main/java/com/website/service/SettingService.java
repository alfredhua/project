package com.website.service;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.mybatis.enums.ConditionEnum;
import com.common.util.PageUtil;
import com.website.dao.SettingMapper;
import com.website.entity.Setting;
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

    public PageBean<Setting> listSettingByPage(PageRequest pageRequest) {
        PageBean<Setting> pageBean = PageUtil.getPageBean(pageRequest.getPage_num(),pageRequest.getPage_size(),pageRequest.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        List<Setting> settingRespDTOS = settingMapper.listAll(entityWrapper);
        if (ObjectUtils.isEmpty(settingRespDTOS)){
            pageBean.setTotal(0);
            return pageBean;
        }
//        for (Setting settingRespDTO:settingRespDTOS) {
//            settingRespDTO.setChildren(listChildren(settingRespDTO.getId()));
//        }
//        pageBean.setList(settingRespDTOS);
        pageBean.setTotal(settingMapper.listCount(entityWrapper));
        return pageBean;
    }

    
    public boolean updateStatus(long id, short status){
        Setting setting=new Setting();
        setting.setId(id);
        setting.setStatus(status);
        return settingMapper.updateById(setting);
    }

    
    public List<Setting> listSettingByPartnerId(long partnerId) {
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.addCondition("parent_id", ConditionEnum.eq,partnerId);
        return settingMapper.listAll(entityWrapper);
    }


    private List<Setting> listChildren(long parent_id){
        List<Setting> settingRespDTOS = listChildrenSetting(null);
        if (ObjectUtils.isEmpty(settingRespDTOS)){
            return null;
        }else{
            for (Setting setting:settingRespDTOS) {
//                setting.setChildren( listChildren(settingRespDTO.getId()));
            }
        }
        return settingRespDTOS;
    }

     
     public Setting getById(Long id) {
         return settingMapper.queryById(id);
     }


    public List<Setting> listChildrenSetting(String type) {
//        SettingRespDTO settingRespDTO=settingMapper.(type);
//        if (ObjectUtils.isEmpty(settingRespDTO)){
           return new ArrayList<>();
//        }
//        return settingMapper.listSettingByPartnerId(settingRespDTO.getId());
    }
}
