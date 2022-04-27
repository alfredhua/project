package com.pro.site.controller;

import com.blog.service.ArticleService;
import com.common.api.entity.response.PageBean;
import com.common.aspect.annotation.LimitTime;
import com.common.util.BeanCopyUtil;
import com.pro.api.entity.blog.ArticleListReqDto;
import com.pro.common.controller.BaseController;
import com.pro.site.controller.vo.article.ArticleChartsReqVO;
import com.pro.site.controller.vo.article.ArticleListReqVO;
import com.pro.site.controller.vo.article.ArticleReqVO;
import com.pro.site.controller.vo.article.ArticleRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
@RestController
@RequestMapping(value = SiteWebsiteUrl.BLOG_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class SiteArticleController extends BaseController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = SiteWebsiteUrl.GET_ARTICLE)
    @LimitTime
    public ArticleRespVO getById(@RequestBody @Valid ArticleReqVO articleReqVO){
        return resultReturn(articleService.getById(articleReqVO.getId()), ArticleRespVO.class);
    }

    @RequestMapping(value = SiteWebsiteUrl.LIST_ARTICLE)
    @LimitTime
    public PageBean<ArticleRespVO> listArticleByPage(@RequestBody @Valid ArticleListReqVO articleListReq, BindingResult result){
        ArticleListReqDto articleListReqDto = BeanCopyUtil.copy(articleListReq, ArticleListReqDto.class);
        articleListReqDto.setStatus((short)1);
        return pageResultReturn(
                articleService.listArticleByPage(articleListReqDto),
                ArticleRespVO.class);
    }


    @RequestMapping(value = SiteWebsiteUrl.CLICK_CHARTS)
    @LimitTime
    public List<ArticleRespVO> listArticleByCharts(@RequestBody ArticleChartsReqVO articleChartsReqVO){
        return listReturn( articleService.listArticleForCharts(articleChartsReqVO.getType()),ArticleRespVO.class);
    }

    @RequestMapping(value = SiteWebsiteUrl.LIST_ARTICLE_HOME)
    @LimitTime
    public List<ArticleRespVO> listArticleHome(){
        return listReturn( articleService.listArticleHome(),ArticleRespVO.class);
    }

}
