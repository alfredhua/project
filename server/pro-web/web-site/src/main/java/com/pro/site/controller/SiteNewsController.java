package com.pro.site.controller;

import com.common.api.entity.response.PageBean;
import com.common.aspect.annotation.LimitTime;
import com.common.util.BeanCopyUtil;
import com.pro.api.entity.news.NewsListReqDto;
import com.pro.controller.BaseController;
import com.pro.site.controller.vo.news.NewsListReqVO;
import com.pro.site.controller.vo.news.NewsResponseVO;
import com.website.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author guozhenhua
 * @date 2019/07/14
 */
@RestController
@RequestMapping(value = SiteWebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class SiteNewsController extends BaseController {

    @Autowired
    NewsService newsService;

    @RequestMapping(value = SiteWebsiteUrl.LIST_NEWS_HOME)
    @LimitTime
    public List<NewsResponseVO> listNewsHome(){
        return listReturn(newsService.listNewsHome(), NewsResponseVO.class);
    }

    @RequestMapping(value = SiteWebsiteUrl.LIST_NEWS_PAGE)
    @LimitTime
    public PageBean<NewsResponseVO> listNewsPage(@RequestBody @Valid NewsListReqVO newsListReqVO ){
        return pageResultReturn(
                newsService.listNewsByPage(BeanCopyUtil.copy(newsListReqVO, NewsListReqDto.class)),
                NewsResponseVO.class);
    }

    @RequestMapping(value = SiteWebsiteUrl.GET_NEWS_BY_ID)
    @LimitTime
    public NewsResponseVO getNewsById(@PathVariable("id") long id){
        return resultReturn(newsService.getById(id),NewsResponseVO.class);
    }


}
