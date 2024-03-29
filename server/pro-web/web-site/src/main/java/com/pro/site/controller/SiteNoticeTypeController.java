package com.pro.site.controller;

import com.common.aspect.annotation.LimitTime;
import com.pro.common.controller.BaseController;
import com.pro.site.controller.vo.noticetype.NoticeTypeRespVO;
import com.website.service.NoticeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auth guozhenhua
 */
@RestController
@RequestMapping(value = SiteWebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class SiteNoticeTypeController extends BaseController {

    @Autowired
    NoticeTypeService noticeTypeService;

    @RequestMapping(value = SiteWebsiteUrl.LIST_ACTIVE_NOTICE_TYPE)
    @LimitTime
    public List<NoticeTypeRespVO> listAllActive(){
        return listReturn(noticeTypeService.listAllActive(), NoticeTypeRespVO.class);
    }



}
