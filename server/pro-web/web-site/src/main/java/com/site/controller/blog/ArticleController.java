package com.site.controller.blog;

import com.blog.service.ArticleService;
import com.common.aspect.annotation.LimitTime;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.blog.dto.ArticleListReqDTO;
import com.pro.blog.dto.entity.Article;
import com.site.controller.blog.vo.article.ArticleChartsReqVO;
import com.site.controller.blog.vo.article.ArticleListReqVO;
import com.site.controller.blog.vo.article.ArticleReqVO;
import com.site.controller.blog.vo.article.ArticleRespVO;
import com.site.controller.common.BaseController;
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
@RequestMapping(value = BlogUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class ArticleController  extends BaseController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = BlogUrl.GET_ARTICLE)
    @LimitTime
    public ArticleRespVO getById(@RequestBody @Valid ArticleReqVO articleReqVO, BindingResult result){
        Article article = articleService.getById(articleReqVO.getId());
        return resultReturn(article,ArticleRespVO.class);
    }

    @RequestMapping(value = BlogUrl.LIST_ARTICLE)
    @LimitTime
    public PageBean<ArticleRespVO> listArticleByPage(@RequestBody @Valid ArticleListReqVO articleListReq, BindingResult result){
        ArticleListReqDTO articleListReqDTO = BeanCopyUtil.copy(articleListReq, ArticleListReqDTO.class);
        articleListReqDTO.setStatus((short)1);
        PageBean<Article> pageBean = articleService.listArticleByPage(articleListReqDTO);
        return pageResultReturn(pageBean,ArticleRespVO.class);
    }


    @RequestMapping(value = BlogUrl.CLICK_CHARTS)
    @LimitTime
    public List<ArticleRespVO> listArticleByCharts(@RequestBody ArticleChartsReqVO articleChartsReqVO){
        return listReturn( articleService.listArticleByCharts(articleChartsReqVO.getType()),ArticleRespVO.class);
    }

    @RequestMapping(value = BlogUrl.LIST_ARTICLE_HOME)
    @LimitTime
    public List<ArticleRespVO> listArticleHome(){
        return listReturn( articleService.listArticleHome(),ArticleRespVO.class);
    }

}
