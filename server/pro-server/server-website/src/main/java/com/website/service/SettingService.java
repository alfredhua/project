package com.website.service;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.mybatis.enums.ConditionEnum;
import com.common.util.BeanCopyUtil;
import com.common.util.PageUtil;
import com.pro.api.entity.setting.SettingRespDto;
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

    public PageBean<SettingRespDto> listSettingByPage(PageRequest pageRequest) {
        PageBean<SettingRespDto> pageBean = PageUtil.getPageBean(pageRequest.getPage_num(),pageRequest.getPage_size(),pageRequest.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.addCondition("partner_id",ConditionEnum.eq,0L);
        List<Setting> settingList = settingMapper.listAll(entityWrapper);
        if (ObjectUtils.isEmpty(settingList)){
            pageBean.setTotal(0);
            return pageBean;
        }
        List<SettingRespDto> list = BeanCopyUtil.copyList(settingList, SettingRespDto.class);
        for (SettingRespDto SettingRespDto:list) {
            SettingRespDto.setChildren(listSettingByPartnerId(SettingRespDto.getId()));
        }
        pageBean.setList(list);
        pageBean.setTotal(settingMapper.listCount(entityWrapper));
        return pageBean;
    }

    public List<SettingRespDto> listSettingByPartnerId(long partnerId) {
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.addCondition("partner_id", ConditionEnum.eq,partnerId);
        List<Setting> settingList = settingMapper.listAll(entityWrapper);
        return BeanCopyUtil.copyList(settingList, SettingRespDto.class);
    }


    private List<SettingRespDto> listChildren(long parent_id){
        List<SettingRespDto> settingRespDTOS = listChildrenSetting(null);
        if (ObjectUtils.isEmpty(settingRespDTOS)){
            return null;
        }else{
            for (SettingRespDto settingRespDto:settingRespDTOS) {
                settingRespDto.setChildren(listChildren(settingRespDto.getId()));
            }
        }
        return settingRespDTOS;
    }

    
    public boolean updateStatus(long id, short status){
        Setting setting=new Setting();
        setting.setId(id);
        setting.setStatus(status);
        return settingMapper.updateById(setting);
    }

    public Setting getById(Long id) {
        return settingMapper.queryById(id);
     }


    public List<SettingRespDto> listChildrenSetting(String type) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.addCondition("type",ConditionEnum.eq,type);
        List<Setting> settingList = settingMapper.listAll(entityWrapper);
        return BeanCopyUtil.copyList(settingList, SettingRespDto.class);
    }
}
