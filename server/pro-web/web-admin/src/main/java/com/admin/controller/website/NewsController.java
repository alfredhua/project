package com.admin.controller.website;

import com.admin.controller.common.AdminBaseController;
import com.admin.controller.website.vo.news.*;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.website.dto.NewsListReqDTO;
import com.pro.website.dto.entity.News;
import com.website.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 * @date 2019/07/12
 */
@Api(tags="新闻管理")
@RestController
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class NewsController  extends AdminBaseController {


    @Autowired
    NewsService newsService;

    @ApiOperation(value = "新闻创建")
    @RequestMapping(value = WebsiteUrl.CREATE_NEWS)
    public void createNews(@RequestBody @Valid NewsCreateReqVO newsCreateReqVO, BindingResult result){
        News newsReqDTO = BeanCopyUtil.copy(newsCreateReqVO, News.class);
        newsService.createNews(newsReqDTO);
    }

    @ApiOperation(value = "新闻更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_NEWS)
    public void updateNews(@RequestBody @Valid NewsUpdateReqVO newsUpdateReqVO, BindingResult result) throws Exception {
        News newsReqDTO = BeanCopyUtil.copy(newsUpdateReqVO, News.class);
        newsService.updateNews(newsReqDTO);
    }

    @ApiOperation(value = "新闻发布撤回")
    @RequestMapping(value = WebsiteUrl.UPDATE_NEWS_PUBLISH)
    public void updateNewsPublish(@RequestBody @Valid NewsUpdatePublishReqVO reqVO, BindingResult result) throws Exception {
        newsService.updateNewsPublish(reqVO.getId(),reqVO.getPublish());
    }

    @ApiOperation(value = "新闻获取")
    @RequestMapping(value = WebsiteUrl.GET_NEWS)
    public NewsRespVO getById(@PathVariable("id") String id){
        return resultReturn(newsService.getById(id),NewsRespVO.class);
    }

    @ApiOperation(value = "新闻删除")
    @RequestMapping(value = WebsiteUrl.DEL_NEWS)
    public void delNews(@PathVariable("id") String id) throws Exception {
       newsService.delNews(id);
    }


    @ApiOperation(value = "新闻列表")
    @RequestMapping(value = WebsiteUrl.LIST_NEWS)
    public PageBean<NewsRespVO> listNewsByPage(@RequestBody @Valid NewsListReqVO newsListReqVO, BindingResult result){
        NewsListReqDTO newsListReqDTO = BeanCopyUtil.copy(newsListReqVO, NewsListReqDTO.class);
        return pageResultReturn(newsService.listNewsByPage(newsListReqDTO),NewsRespVO.class);
    }

}
