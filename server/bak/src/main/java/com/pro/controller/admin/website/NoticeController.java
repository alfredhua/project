package com.pro.controller.admin.website;

import com.pro.controller.admin.website.vo.article.ArticleCreateRequest;
import com.pro.controller.admin.website.vo.article.ArticleListReqVo;
import com.pro.controller.admin.website.vo.article.ArticleRespVo;
import com.pro.controller.admin.website.vo.article.ArticleUpdateRequest;
import com.pro.controller.common.AdminBaseController;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.website.dto.NoticeListReqDTO;
import com.pro.website.dto.entity.Notice;
import com.website.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 * @date 2018/12/20
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class NoticeController  extends AdminBaseController {

    @Autowired
    NoticeService noticeService;

    @ApiOperation(value = "文章创建")
    @RequestMapping(value = WebsiteUrl.CREATE_NOTICE)
    public void createNotice(@RequestBody @Valid ArticleCreateRequest articleCreateRequest ){
        Notice noticeReqDTO = BeanCopyUtil.copy(articleCreateRequest, Notice.class);
        noticeService.createNotice(noticeReqDTO);
    }

    @ApiOperation(value = "文章更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_NOTICE)
    public void  updateNotice(@RequestBody @Valid ArticleUpdateRequest articleUpdateRequest ) throws Exception {
        Notice noticeReqDTO = BeanCopyUtil.copy(articleUpdateRequest, Notice.class);
        noticeService.updateNotice(noticeReqDTO);
    }

    @ApiOperation(value = "文章发布")
    @RequestMapping(value = WebsiteUrl.UPDATE_PUBLISH_NOTICE)
    public void updateArticlePublish(@PathVariable("id") long id) throws Exception {
        noticeService.updateNoticePublish(id);
    }

    @ApiOperation(value = "文章详情")
    @RequestMapping(value = WebsiteUrl.GET_NOTICE)
    public ArticleRespVo getById(@PathVariable("id") long id) throws Exception {
        return resultReturn(noticeService.getById(id), ArticleRespVo.class);
    }

    @ApiOperation(value = "文章删除")
    @RequestMapping(value = WebsiteUrl.DEL_NOTICE)
    public void delArticle(@PathVariable("id") long id) throws Exception {
         noticeService.delNotice(id);
    }

    @ApiOperation(value = "文章列表")
    @RequestMapping(value = WebsiteUrl.LIST_NOTICE)
    public PageBean<ArticleRespVo> listArticleByPage(@RequestBody @Valid ArticleListReqVo articleListReqVo ){
        NoticeListReqDTO noticeListReqDTO = BeanCopyUtil.copy(articleListReqVo, NoticeListReqDTO.class);
        PageBean<Notice> pageBean = noticeService.listNoticeByPage(noticeListReqDTO);
        return pageResultReturn(pageBean,ArticleRespVo.class);
    }

}
