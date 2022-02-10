package com.site.controller.website;

import com.common.aspect.annotation.LimitTime;
import com.pro.website.dto.entity.SettingDetail;
import com.site.controller.common.BaseController;
import com.site.controller.website.vo.setting.SettingDetailRespVO;
import com.site.controller.website.vo.setting.SettingResponseVO;
import com.website.service.SettingDetailService;
import com.website.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author guozhenhua
 * @date 2019/07/13
 */
@RestController
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class SettingController extends BaseController {

    @Autowired
    SettingService settingService;

    @Autowired
    SettingDetailService settingDetailService;

    @RequestMapping(value = WebsiteUrl.LIST_SETTING)
    @LimitTime
    public List<SettingResponseVO> listSetting(){
        return listReturn(settingService.listSettingByPartnerId(0),SettingResponseVO.class);
    }

    @RequestMapping(value = WebsiteUrl.LIST_CHILDREN_SETTING)
    @LimitTime
    public List<SettingResponseVO> listChildrenSetting(@PathVariable("type") String type){
        return listReturn(settingService.listChildrenSetting(type),SettingResponseVO.class);
    }

    @RequestMapping(value = WebsiteUrl.GET_SETTING)
    @LimitTime
    public SettingDetailRespVO getByType(@PathVariable("type") String type){
        SettingDetail settingDetail = settingDetailService.getByType(type);
        if (ObjectUtils.isEmpty(settingDetail)){
            return new SettingDetailRespVO();
        }
        return resultReturn(settingDetail, SettingDetailRespVO.class);
    }


}
