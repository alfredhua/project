package com.pro.admin.controller.website;

import com.common.api.entity.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.admin.controller.website.vo.notice.NoticeCreateRequest;
import com.pro.admin.controller.website.vo.notice.NoticeListReqVo;
import com.pro.admin.controller.website.vo.notice.NoticeRespVo;
import com.pro.admin.controller.website.vo.notice.NoticeUpdateRequest;
import com.pro.api.entity.website.NoticeListReqDto;
import com.pro.controller.common.BaseController;
import com.website.entity.Notice;
import com.website.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 * @date 2018/12/20
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping(value = WebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class NoticeController  extends BaseController {

    @Autowired
    NoticeService noticeService;

    @ApiOperation(value = "文章创建")
    @RequestMapping(value = WebsiteUrl.CREATE_NOTICE)
    public void createNotice(@RequestBody @Valid NoticeCreateRequest noticeCreateRequest ){
        noticeService.createNotice(BeanCopyUtil.copy(noticeCreateRequest, Notice.class));
    }

    @ApiOperation(value = "文章更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_NOTICE)
    public void  updateNotice(@RequestBody @Valid NoticeUpdateRequest noticeUpdateRequest ) throws Exception {
        noticeService.updateNotice(BeanCopyUtil.copy(noticeUpdateRequest, Notice.class));
    }

    @ApiOperation(value = "文章发布")
    @RequestMapping(value = WebsiteUrl.UPDATE_PUBLISH_NOTICE)
    public void updateArticlePublish(@PathVariable("id") long id) throws Exception {
        noticeService.updateNoticePublish(id);
    }

    @ApiOperation(value = "文章详情")
    @RequestMapping(value = WebsiteUrl.GET_NOTICE)
    public NoticeRespVo getById(@PathVariable("id") long id) throws Exception {
        return resultReturn(noticeService.getById(id), NoticeRespVo.class);
    }

    @ApiOperation(value = "文章删除")
    @RequestMapping(value = WebsiteUrl.DEL_NOTICE)
    public void delArticle(@PathVariable("id") long id) throws Exception {
         noticeService.delNotice(id);
    }

    @ApiOperation(value = "文章列表")
    @RequestMapping(value = WebsiteUrl.LIST_NOTICE)
    public PageBean<NoticeRespVo> listArticleByPage(@RequestBody @Valid NoticeListReqVo noticeListReqVo ){
        NoticeListReqDto noticeListReqDto = BeanCopyUtil.copy(noticeListReqVo, NoticeListReqDto.class);
        PageBean<Notice> pageBean = noticeService.listNoticeByPage(noticeListReqDto);
        return pageResultReturn(pageBean,NoticeRespVo.class);
    }

}
