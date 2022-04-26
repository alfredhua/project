package com.website.service;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.pro.api.entity.news.NewsListReqDto;
import com.website.dao.NewsMapper;
import com.website.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/07/12
 */
@Component
public class NewsService  {

    @Autowired
    NewsMapper newsMapper;


    public void createNews(News newsReqDTO) {
        newsReqDTO.setId(IDGenerateUtil.generateId());
        newsMapper.insert(newsReqDTO);
    }

    public News getById(Long id) {
        return newsMapper.queryById(id);
    }

    public boolean updateNews(News newsReqDTO){
        return newsMapper.updateById(newsReqDTO);
    }


    public boolean delNews(Long id)  {
        return newsMapper.deleteById(id);
    }


    public PageBean<News> listNewsByPage(NewsListReqDto newsListReqDto) {
        PageBean<News> pageBean = PageUtil.getPageBean(newsListReqDto.getPage_num(),newsListReqDto.getPage_size(),newsListReqDto.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(newsMapper.listByPage(pageBean.getPage_num(),pageBean.getPage_size(),entityWrapper));
        pageBean.setTotal(newsMapper.listCount(entityWrapper));
        return pageBean;
    }


    public boolean updateNewsPublish(long id, short publish){
        News news=new News();
        news.setId(id);
        news.setPublish(publish);
        return newsMapper.updateById(news);
    }

    public List<News> listNewsHome() {
        EntityWrapper entityWrapper=new EntityWrapper();
        return newsMapper.listAll(entityWrapper);
    }

}
