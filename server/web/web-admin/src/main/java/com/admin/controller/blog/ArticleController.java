package com.admin.controller.blog;

import com.blog.dto.entity.Article;
import com.blog.dto.ArticleListReqDTO;
import com.blog.service.ArticleService;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.admin.controller.blog.vo.article.*;
import com.admin.controller.common.AdminBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 * @date 2019/08/31
 */
@Api(tags="文章管理")
@RestController
@RequestMapping(value = BlogUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class ArticleController  extends AdminBaseController {

    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "文章创建")
    @RequestMapping(value = BlogUrl.CREATE_ARTICLE)
    public void createArticle(@RequestBody @Valid ArticleCreateReqVO articleCreateRequest, BindingResult result){
        Article article = BeanCopyUtil.copy(articleCreateRequest, Article.class);
        articleService.createArticle(article);
    }

    @ApiOperation(value = "文章更新")
    @RequestMapping(value = BlogUrl.UPDATE_ARTICLE)
    public void updateArticle(@RequestBody @Valid ArticleUpdateReqVO articleUpdateRequest, BindingResult result) throws Exception {
        Article article = BeanCopyUtil.copy(articleUpdateRequest, Article.class);
        articleService.updateArticle(article);
    }

    @ApiOperation(value = "获取文章")
    @RequestMapping(value = BlogUrl.GET_ARTICLE)
    public ArticleRespVO getById(@PathVariable("id") long id){
        return resultReturn(articleService.getById(id),ArticleRespVO.class);
    }

    @ApiOperation(value = "删除文章")
    @RequestMapping(value = BlogUrl.DEL_ARTICLE)
    public void delArticle(@PathVariable("id") long id) throws Exception {
         articleService.delArticle(id);
    }

    @ApiOperation(value = "更新文章状态")
    @RequestMapping(value = BlogUrl.UPDATE_ARTICLE_STATUS)
    public void updateArticleStatus(@RequestBody @Valid ArticleStatusReqVO articleStatusReqVO) throws Exception {
         articleService.updateArticleStatus(articleStatusReqVO.getId(),articleStatusReqVO.getStatus());
    }

    @ApiOperation(value = "更新文章列表")
    @RequestMapping(value = BlogUrl.LIST_ARTICLE)
    public PageBean<ArticleRespVO> listArticleByPage(@RequestBody @Valid ArticleListReqVO articleListReq, BindingResult result){
        ArticleListReqDTO articleListReqDTO = BeanCopyUtil.copy(articleListReq, ArticleListReqDTO.class);
        return pageResultReturn(articleService.listArticleByPage(articleListReqDTO),ArticleRespVO.class);
    }

}
