package com.blog.service;

import com.blog.constant.BlogRedisStaticKey;
import com.blog.dao.ArticleMapper;
import com.blog.entity.Article;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.mybatis.enums.ConditionEnum;
import com.common.redis.client.RedisClient;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.pro.api.entity.blog.ArticleListReqDto;
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
        articleMapper.insert(article);
    }

    public Article getById(long id) {
        articleMapper.updateClickCount(id);
        return articleMapper.getById(id);
    }


    public boolean updateArticle(Article article){
        return articleMapper.updateById(article);
    }

    public boolean delArticle(long id) {
        Article article=new Article();
        article.setId(id);
        article.setDel((short)1);
        return articleMapper.updateById(article);
    }

    public PageBean<Article> listArticleByPage(ArticleListReqDto articleListReqDto) {
        PageBean<Article> pageBean = PageUtil.getPageBean(articleListReqDto.getPage_num(), articleListReqDto.getPage_size(),articleListReqDto.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.addCondition("del", ConditionEnum.eq,(short)0);
        if (articleListReqDto.getStatus()==1){
            entityWrapper.addCondition("status", ConditionEnum.eq,(short)1);
        }
        pageBean.setList(articleMapper.listByPage(articleListReqDto.getPage_num(),articleListReqDto.getPage_size(),entityWrapper));
        pageBean.setTotal(articleMapper.listCount(entityWrapper));
        return pageBean;
    }


    public List<Article> listArticleForCharts(String type) {
        return articleMapper.listArticleByCharts(type);
    }

    public List<Article> listArticleHome() {
        List<Article> list=new ArrayList<>();
        if (ObjectUtils.isEmpty(list)){
            return articleMapper.listArticleHome();
        }
        return list;
    }

    public void updateArticleStatus(Long id, short status){
        RedisClient.del(BlogRedisStaticKey.blog_home_list);
        articleMapper.updateArticleStatus(id,status);
    }

}
