package com.pro.site.controller;

import com.common.api.entity.response.PageBean;
import com.common.aspect.annotation.LimitTime;
import com.common.util.BeanCopyUtil;
import com.pro.api.entity.website.NoticeListReqDto;
import com.pro.controller.common.BaseController;
import com.pro.site.controller.vo.notice.NoticeListReqVo;
import com.pro.site.controller.vo.notice.NoticeRespVo;
import com.website.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 */
@RestController
@RequestMapping(value = SiteWebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class SiteNoticeController extends BaseController {

    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = SiteWebsiteUrl.LIST_NOTICE)
    @LimitTime
    public PageBean<NoticeRespVo> listArticleByPage(@RequestBody @Valid NoticeListReqVo articleListReqVo, BindingResult result){
        return pageResultReturn(
                noticeService.listNoticeByPage(BeanCopyUtil.copy(articleListReqVo, NoticeListReqDto.class)),
                NoticeRespVo.class);
    }

    @RequestMapping(value = SiteWebsiteUrl.GET_NOTICE)
    @LimitTime
    public NoticeRespVo getById(@PathVariable("id") long id) throws Exception {
        return resultReturn(noticeService.getById(id),NoticeRespVo.class);
    }

}
