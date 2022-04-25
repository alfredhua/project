package com.pro.controller.admin.website;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.api.entity.setting.SettingRespDto;
import com.pro.controller.admin.website.vo.setting.SettingListReqVO;
import com.pro.controller.admin.website.vo.setting.SettingRespVO;
import com.pro.controller.admin.website.vo.setting.UpdateStatusRequestVO;
import com.pro.controller.common.BaseController;
import com.website.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Api(tags = "网站设置")
@RestController
@RequestMapping(value = WebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class SettingController  extends BaseController {


    @Autowired
    SettingService settingService;

    @ApiOperation(value = "网站设置列表")
    @RequestMapping(value = WebsiteUrl.LIST_SETTING)
    public PageBean<SettingRespVO> listSettingByPage(@RequestBody @Valid SettingListReqVO settingListReqVO ){
        PageBean<SettingRespDto> settingRespDtoPageBean = settingService.listSettingByPage(new PageRequest(settingListReqVO.getPage_num(), settingListReqVO.getPage_size()));
        PageBean<SettingRespVO> settingRespVOPageBean = new PageBean<>();
        List<SettingRespVO> resultList=new ArrayList<>();
        if (settingRespDtoPageBean.getList()!=null){
            for (SettingRespDto settingRespDto:settingRespDtoPageBean.getList()) {
                SettingRespVO settingRespVO = BeanCopyUtil.copy(settingRespDto, SettingRespVO.class);
                List<SettingRespVO> children = copyChildren(settingRespDto.getChildren());
                settingRespVO.setChildren(children);
                resultList.add(settingRespVO);
            }
        }
        settingRespVOPageBean.setList(resultList);
        settingRespVOPageBean.setTotal(settingRespDtoPageBean.getTotal());
        settingRespVOPageBean.setPage_num(settingRespDtoPageBean.getPage_num());
        settingRespVOPageBean.setPage_size(settingRespDtoPageBean.getPage_size());
        return settingRespVOPageBean;
    }

    private List<SettingRespVO> copyChildren(List<SettingRespDto> children){
        if (ObjectUtils.isEmpty(children)){
            return null;
        }
        List<SettingRespVO> list=new ArrayList<>();
        for (SettingRespDto settingRespDTO:children) {
            SettingRespVO copy = BeanCopyUtil.copy(settingRespDTO, SettingRespVO.class);
            copy.setChildren(copyChildren(settingRespDTO.getChildren()));
            list.add(copy);
        }
        return list;
    }

    @ApiOperation(value = "关于我们下列表设置")
    @RequestMapping(value = WebsiteUrl.LIST_CHILDREN_SETTING)
    public List<SettingRespVO> listChildrenSetting(@PathVariable("type") String type){
        return listReturn(settingService.listChildrenSetting(type),SettingRespVO.class);
    }

    @ApiOperation(value = "更新设置状态")
    @RequestMapping(value = WebsiteUrl.UPDATE_SETTING_STATUS)
    public void updateStatus(@RequestBody @Valid UpdateStatusRequestVO updateStatusRequestVO ) throws Exception {
        settingService.updateStatus(updateStatusRequestVO.getId(),updateStatusRequestVO.getStatus());
    }

}
