package com.blog.service;

import com.blog.constant.BlogRedisStaticKey;
import com.blog.dao.ArticleMapper;
import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.middle.redis.RedisUtil;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.pro.blog.dto.ArticleListReqDTO;
import com.pro.blog.dto.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
@Component
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;


    public void createArticle(Article article) {
        article.setId(IDGenerateUtil.generateId());
        article.setCreate_time(LocalDateTime.now());
        article.setClick_count(0);
        articleMapper.createArticle(article);
    }

    public Article getById(long id) {
        articleMapper.updateClickCount(id);
        return articleMapper.getById(id);
    }


    public void updateArticle(Article article) throws Exception {
        if(articleMapper.updateArticle(article)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }


    public void delArticle(long id) throws Exception  {
        if(articleMapper.delArticle(id)){
           return;
        }
        throw ResultException.error(SysErrorCodeEnum.DEL_ERROR);
    }

    public PageBean<Article> listArticleByPage(ArticleListReqDTO articleListReqDTO) {
        PageBean<Article> pageBean = PageUtil.validatePage(articleListReqDTO.getPage_num(),
                articleListReqDTO.getPage_size(),articleListReqDTO.getOffset());
        pageBean.setList(articleMapper.listArticleByPage(articleListReqDTO));
        pageBean.setTotal(articleMapper.listArticleCount(articleListReqDTO));
        return pageBean;
    }


    public List<Article> listArticleByCharts(String type) {
        return articleMapper.listArticleByCharts(type);
    }


    public List<Article> listArticleHome() {
        List<Article> list=new ArrayList<>();
        if (ObjectUtils.isEmpty(list)){
            return articleMapper.listArticleHome();
        }
        return list;
    }


    public void updateArticleStatus(Long id, short status) throws Exception {
        RedisUtil.del(BlogRedisStaticKey.blog_home_list);
        if (articleMapper.updateArticleStatus(id,status)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.DEL_ERROR);
    }

}
