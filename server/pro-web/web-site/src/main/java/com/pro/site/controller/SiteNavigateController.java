package com.pro.site.controller;

import com.pro.controller.common.BaseController;
import com.pro.site.controller.vo.Navigate.NavigateListReqVO;
import com.pro.site.controller.vo.Navigate.NavigateRespVO;
import com.website.entity.Navigate;
import com.website.service.NavigateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2021/08/23
 */
@RestController
@RequestMapping(value = "/site/navigate",method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class SiteNavigateController extends BaseController {

    @Autowired
    NavigateService navigateService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public List<NavigateRespVO> listNavigateByType(@RequestBody @Valid NavigateListReqVO navigateListReq){
        List<Navigate> list= navigateService.listNavigateByType(navigateListReq.getOne_type());
        return listReturn(list,NavigateRespVO.class);
    }

}
