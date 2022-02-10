package com.admin.controller.website;

import com.admin.controller.common.AdminBaseController;
import com.admin.controller.website.vo.setting.SettingListReqVO;
import com.admin.controller.website.vo.setting.SettingRespVO;
import com.admin.controller.website.vo.setting.UpdateStatusRequestVO;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.website.dto.SettingListReqDTO;
import com.website.dto.SettingRespDTO;
import com.website.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class SettingController  extends AdminBaseController {


    @Autowired
    SettingService settingService;

    @ApiOperation(value = "网站设置列表")
    @RequestMapping(value = WebsiteUrl.LIST_SETTING)
    public PageBean<SettingRespVO> listSettingByPage(@RequestBody @Valid SettingListReqVO settingListReqVO, BindingResult result){
        SettingListReqDTO settingListReqDTO = BeanCopyUtil.copy(settingListReqVO, SettingListReqDTO.class);
        PageBean<SettingRespDTO> pageBean = settingService.listSettingByPage(settingListReqDTO);
        return pageResultReturn(pageBean,SettingRespVO.class);
    }

    private List<SettingRespVO> copyChildren(List<SettingRespDTO> children){
        if (ObjectUtils.isEmpty(children)){
            return null;
        }
        List<SettingRespVO> list=new ArrayList<>();
        for (SettingRespDTO settingRespDTO:children) {
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
    public void updateStatus(@RequestBody @Valid UpdateStatusRequestVO updateStatusRequestVO, BindingResult result) throws Exception {
        settingService.updateStatus(updateStatusRequestVO.getId(),updateStatusRequestVO.getStatus());
    }

}
