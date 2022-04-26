package com.pro.controller.site.website;

import com.common.aspect.annotation.LimitTime;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.site.controller.common.BaseController;
import com.site.controller.website.vo.notice.NoticeListReqVo;
import com.site.controller.website.vo.notice.NoticeRespVo;
import com.website.dto.entity.Notice;
import com.website.dto.NoticeListReqDTO;
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
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class NoticeController extends BaseController {

    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = WebsiteUrl.LIST_NOTICE)
    @LimitTime
    public PageBean<NoticeRespVo> listArticleByPage(@RequestBody @Valid NoticeListReqVo articleListReqVo, BindingResult result){
        NoticeListReqDTO noticeListReqDTO = BeanCopyUtil.copy(articleListReqVo, NoticeListReqDTO.class);
        PageBean<Notice> pageBean = noticeService.listNoticeByPage(noticeListReqDTO);
        return pageResultReturn(pageBean,NoticeRespVo.class);
    }

    @RequestMapping(value = WebsiteUrl.GET_NOTICE)
    @LimitTime
    public NoticeRespVo getById(@PathVariable("id") long id) throws Exception {
        return resultReturn(noticeService.getById(id),NoticeRespVo.class);
    }

}
