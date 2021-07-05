package com.site.controller.website;

import com.common.aspect.annotation.LimitTime;
import com.common.domain.response.JSONResult;
import com.site.controller.common.BaseController;
import com.site.controller.website.vo.noticetype.NoticeTypeRespVO;
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
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class NoticeTypeController extends BaseController {

    @Autowired
    NoticeTypeService noticeTypeService;

    @RequestMapping(value =WebsiteUrl.LIST_ACTIVE_NOTICE_TYPE)
    @LimitTime
    public List<NoticeTypeRespVO> listAllActive(){
        return listReturn(noticeTypeService.listAllActive(), NoticeTypeRespVO.class);
    }



}
