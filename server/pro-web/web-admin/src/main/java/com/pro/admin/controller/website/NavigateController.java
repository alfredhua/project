package com.pro.admin.controller.website;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.admin.controller.website.vo.navigate.NavigateListReqVO;
import com.pro.admin.controller.website.vo.navigate.NavigateReqVO;
import com.pro.admin.controller.website.vo.navigate.NavigateRespVO;
import com.pro.controller.BaseController;
import com.website.entity.Navigate;
import com.website.service.NavigateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 * @date 2021/08/23
 */
@RestController
@RequestMapping(value = "/admin/navigate",method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class NavigateController extends BaseController {

    @Autowired
    NavigateService navigateService;

    /**
     * 创建
     */
    @RequestMapping(value = "/create")
    public void createNavigate(@RequestBody @Valid NavigateReqVO navigateReqVO ){
        Navigate navigate = BeanCopyUtil.copy(navigateReqVO, Navigate.class);
        navigateService.createNavigate(navigate);
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update")
    public void updateNavigate(@RequestBody @Valid NavigateReqVO navigateReqVO ){
        Navigate navigate = BeanCopyUtil.copy(navigateReqVO, Navigate.class);
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
    public PageBean<NavigateRespVO> listNavigateByPage(@RequestBody @Valid NavigateListReqVO navigateListReq ){
        return pageResultReturn(
                navigateService.listNavigateByPage(new PageRequest(navigateListReq.getPage_num(),navigateListReq.getPage_size()))
                ,NavigateRespVO.class);
    }

}
