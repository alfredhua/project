package com.pro.admin.controller.website;

import com.common.util.BeanCopyUtil;
import com.pro.admin.controller.website.vo.settingdetail.SettingDetailRespVO;
import com.pro.admin.controller.website.vo.settingdetail.SettingDetailUpdateReqVO;
import com.pro.controller.common.BaseController;
import com.website.entity.SettingDetail;
import com.website.service.SettingDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Api(tags = "网站设置详情")
@RestController
@RequestMapping(value = WebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class SettingDetailController  extends BaseController {


    @Autowired
    SettingDetailService settingDetailService;

    @ApiOperation(value = "设置更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_SETTING_DETAIL)
    public void updateSettingDetail(@RequestBody @Valid SettingDetailUpdateReqVO settingDetailUpdateReqVO ) throws Exception {
        settingDetailService.updateSettingDetail(
                BeanCopyUtil.copy(settingDetailUpdateReqVO, SettingDetail.class)
        );
    }

    @ApiOperation(value = "设置获取")
    @RequestMapping(value = WebsiteUrl.GET_SETTING_DETAIL)
    public SettingDetailRespVO getByType(@PathVariable("type") String type){
         SettingDetail settingDetail = settingDetailService.getByType(type);
        if (ObjectUtils.isEmpty(settingDetail)){
            return new SettingDetailRespVO();
        }
        return resultReturn(settingDetail,SettingDetailRespVO.class);
    }



}
