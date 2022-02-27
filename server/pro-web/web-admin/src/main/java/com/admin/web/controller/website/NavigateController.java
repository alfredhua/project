package com.admin.web.controller.website;

import com.admin.web.controller.common.AdminBaseController;
import com.admin.web.controller.website.vo.Navigate.NavigateListReqVO;
import com.admin.web.controller.website.vo.Navigate.NavigateReqVO;
import com.admin.web.controller.website.vo.Navigate.NavigateRespVO;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.website.dto.NavigateListReqDTO;
import com.pro.website.dto.NavigateReqDTO;
import com.pro.website.dto.entity.Navigate;
import com.website.service.NavigateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 * @date 2021/08/23
 */
@RestController
@RequestMapping(value = "/admin/navigate",method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class NavigateController extends AdminBaseController{

    @Autowired
    NavigateService navigateService;

    /**
     * 创建
     */
    @RequestMapping(value = "/create")
    public void createNavigate(@RequestBody @Valid NavigateReqVO navigateReqVO, BindingResult result){
        Navigate navigate = BeanCopyUtil.copy(navigateReqVO, Navigate.class);
        navigateService.createNavigate(navigate);
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update")
    public void updateNavigate(@RequestBody @Valid NavigateReqVO navigateReqVO, BindingResult result){
        Navigate navigate = BeanCopyUtil.copy(navigateReqVO, NavigateReqDTO.class);
         navigateService.updateNavigate(navigate);
    }

    /**
     * 获取
     */
    @RequestMapping(value = "/get")
    public NavigateRespVO getById(@PathVariable("id") long id){
        return resultReturn(navigateService.getById(id),NavigateRespVO.class);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/del")
    public void delNavigate(@PathVariable("id") long id){
        navigateService.delNavigate(id);
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public PageBean<NavigateRespVO> listNavigateByPage(@RequestBody @Valid NavigateListReqVO navigateListReq, BindingResult result){
        NavigateListReqDTO navigateListReqDTO = BeanCopyUtil.copy(navigateListReq, NavigateListReqDTO.class);
        PageBean<Navigate> pageBean= navigateService.listNavigateByPage(navigateListReqDTO);
        return pageResultReturn(pageBean,NavigateRespVO.class);
    }

}
