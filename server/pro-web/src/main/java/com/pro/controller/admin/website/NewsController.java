package com.pro.controller.admin.website;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.controller.admin.website.vo.news.*;
import com.pro.controller.common.BaseController;
import com.website.entity.News;
import com.website.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 * @date 2019/07/12
 */
@Api(tags="新闻管理")
@RestController
@RequestMapping(value = WebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class NewsController  extends BaseController {


    @Autowired
    NewsService newsService;

    @ApiOperation(value = "新闻创建")
    @RequestMapping(value = WebsiteUrl.CREATE_NEWS)
    public void createNews(@RequestBody @Valid NewsCreateReqVO newsCreateReqVO ){
        News newsReqDTO = BeanCopyUtil.copy(newsCreateReqVO, News.class);
        newsService.createNews(newsReqDTO);
    }

    @ApiOperation(value = "新闻更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_NEWS)
    public void updateNews(@RequestBody @Valid NewsUpdateReqVO newsUpdateReqVO ) throws Exception {
        News newsReqDTO = BeanCopyUtil.copy(newsUpdateReqVO, News.class);
        newsService.updateNews(newsReqDTO);
    }

    @ApiOperation(value = "新闻发布撤回")
    @RequestMapping(value = WebsiteUrl.UPDATE_NEWS_PUBLISH)
    public void updateNewsPublish(@RequestBody @Valid NewsUpdatePublishReqVO reqVO ) throws Exception {
        newsService.updateNewsPublish(reqVO.getId(),reqVO.getPublish());
    }

    @ApiOperation(value = "新闻获取")
    @RequestMapping(value = WebsiteUrl.GET_NEWS)
    public NewsRespVO getById(@PathVariable("id") Long id){
        return resultReturn(newsService.getById(id),NewsRespVO.class);
    }

    @ApiOperation(value = "新闻删除")
    @RequestMapping(value = WebsiteUrl.DEL_NEWS)
    public void delNews(@PathVariable("id") long id) throws Exception {
       newsService.delNews(id);
    }


    @ApiOperation(value = "新闻列表")
    @RequestMapping(value = WebsiteUrl.LIST_NEWS)
    public PageBean<NewsRespVO> listNewsByPage(@RequestBody @Valid NewsListReqVO newsListReqVO ){
        return pageResultReturn(
                newsService.listNewsByPage(new PageRequest(newsListReqVO.getPage_num(),newsListReqVO.getPage_size()))
                ,NewsRespVO.class);
    }

}
