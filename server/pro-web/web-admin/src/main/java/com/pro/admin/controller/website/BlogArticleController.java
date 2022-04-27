package com.pro.admin.controller.website;

import com.blog.entity.Article;
import com.blog.service.ArticleService;
import com.common.api.entity.response.PageBean;
import com.common.api.exception.ResultException;
import com.common.util.BeanCopyUtil;
import com.pro.admin.controller.website.vo.article.*;
import com.pro.api.entity.blog.ArticleListReqDto;
import com.pro.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 * @date 2019/08/31
 */
@Api(tags="文章管理")
@RestController
@RequestMapping(value = WebsiteUrl.BLOG_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class BlogArticleController extends BaseController {

    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "文章创建")
    @RequestMapping(value = WebsiteUrl.CREATE_ARTICLE)
    public void createArticle(@RequestBody @Valid ArticleCreateReqVO articleCreateRequest ) throws ResultException {
        Article article = BeanCopyUtil.copy(articleCreateRequest, Article.class);
        articleService.createArticle(article);
    }

    @ApiOperation(value = "文章更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_ARTICLE)
    public void updateArticle(@RequestBody @Valid ArticleUpdateReqVO articleUpdateRequest ) throws Exception {
        Article article = BeanCopyUtil.copy(articleUpdateRequest, Article.class);
        articleService.updateArticle(article);
    }

    @ApiOperation(value = "获取文章")
    @RequestMapping(value = WebsiteUrl.GET_ARTICLE)
    public ArticleRespVO getById(@PathVariable("id") long id){
        return resultReturn(articleService.getById(id), ArticleRespVO.class);
    }

    @ApiOperation(value = "删除文章")
    @RequestMapping(value = WebsiteUrl.DEL_ARTICLE)
    public void delArticle(@PathVariable("id") long id) throws Exception {
         articleService.delArticle(id);
    }

    @ApiOperation(value = "更新文章状态")
    @RequestMapping(value = WebsiteUrl.UPDATE_ARTICLE_STATUS)
    public void updateArticleStatus(@RequestBody @Valid ArticleStatusReqVO articleStatusReqVO) throws Exception {
         articleService.updateArticleStatus(articleStatusReqVO.getId(),articleStatusReqVO.getStatus());
    }

    @ApiOperation(value = "更新文章列表")
    @RequestMapping(value = WebsiteUrl.LIST_ARTICLE)
    public PageBean<ArticleRespVO> listArticleByPage(@RequestBody @Valid ArticleListReqVO articleListReq ){
        ArticleListReqDto articleListReqDTO = BeanCopyUtil.copy(articleListReq, ArticleListReqDto.class);
        return pageResultReturn(articleService.listArticleByPage(articleListReqDTO),ArticleRespVO.class);
    }

}
