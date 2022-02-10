package com.site.controller.website;

import com.common.aspect.annotation.LimitTime;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.website.dto.NewsListReqDTO;
import com.pro.website.dto.entity.News;
import com.site.controller.common.BaseController;
import com.site.controller.website.vo.news.NewsListReqVO;
import com.site.controller.website.vo.news.NewsResponseVO;
import com.website.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author guozhenhua
 * @date 2019/07/14
 */
@RestController
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class NewsController extends BaseController {

    @Autowired
    NewsService newsService;

    @RequestMapping(value = WebsiteUrl.LIST_NEWS_HOME)
    @LimitTime
    public List<NewsResponseVO> listNewsHome(){
        return listReturn(newsService.listNewsHome(),NewsResponseVO.class);
    }

    @RequestMapping(value = WebsiteUrl.LIST_NEWS_PAGE)
    @LimitTime
    public PageBean<NewsResponseVO> listNewsPage(@RequestBody @Valid NewsListReqVO newsListReqVO, BindingResult result){
        NewsListReqDTO newsListReqDTO = BeanCopyUtil.copy(newsListReqVO, NewsListReqDTO.class);
        PageBean<News> pageBean = newsService.listNewsByPageForSite(newsListReqDTO);
        return pageResultReturn(pageBean,NewsResponseVO.class);
    }

    @RequestMapping(value = WebsiteUrl.GET_NEWS_BY_ID)
    @LimitTime
    public NewsResponseVO getNewsById(@PathVariable("id") String id){
        return resultReturn(newsService.getById(id),NewsResponseVO.class);
    }


}
