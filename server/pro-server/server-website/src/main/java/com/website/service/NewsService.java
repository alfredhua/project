package com.website.service;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.website.dao.NewsMapper;
import com.pro.website.dto.entity.News;
import com.pro.website.dto.NewsListReqDTO;
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
        newsMapper.createNews(newsReqDTO);
    }

    public News getById(String id) {
        return newsMapper.getById(id);
    }

    public void updateNews(News newsReqDTO)throws Exception {
        if(newsMapper.updateNews(newsReqDTO)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }


    public void delNews(String id) throws ResultException {
        if(newsMapper.delNews(id)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.DEL_ERROR);
    }


    public PageBean<News> listNewsByPage(NewsListReqDTO newsListReqDTO) {
        PageBean<News> pageBean = PageUtil.validatePage(newsListReqDTO.getPage_num(),
                newsListReqDTO.getPage_size(),newsListReqDTO.getOffset());
        pageBean.setList(newsMapper.listNewsByPage(newsListReqDTO));
        pageBean.setTotal(newsMapper.listNewsCount(newsListReqDTO));
        return pageBean;
    }


    public void updateNewsPublish(long id, short publish) throws Exception {
        if(publish==1?newsMapper.updateNewsPublish(id,publish):newsMapper.updateNewsPublish0(id,publish)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }


    public List<News> listNewsHome() {
        return newsMapper.listNewsHome();
    }

    public PageBean<News> listNewsByPageForSite(NewsListReqDTO newsListReqDTO) {
        PageBean<News> pageBean = PageUtil.validatePage(newsListReqDTO.getPage_num(),
                newsListReqDTO.getPage_size(),newsListReqDTO.getOffset());
        pageBean.setList(newsMapper.listNewsByPageForSite(newsListReqDTO));
        pageBean.setTotal(newsMapper.listNewsCountForSite(newsListReqDTO));
        return pageBean;
    }

}
