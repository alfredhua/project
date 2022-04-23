package com.blog.service;

import com.blog.constant.BlogRedisStaticKey;
import com.blog.dao.ArticleMapper;
import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.redis.client.RedisClient;
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
        articleMapper.insert(article);
    }

    public Article getById(long id) {
        articleMapper.updateClickCount(id);
        return articleMapper.getById(id);
    }


    public boolean updateArticle(Article article){
        return articleMapper.updateById(article);
    }


    public boolean delArticle(long id) throws Exception  {
        Article article=new Article();
        article.setId(id);
        article.setDel((short)1);
        return articleMapper.updateById(article);
    }

    public PageBean<Article> listArticleByPage(ArticleListReqDTO articleListReqDTO, PageRequest request) {
        PageBean<Article> pageBean = PageUtil.getPageBean(request.getPage_num(), request.getPage_size(),request.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(articleMapper.listByPage(request.getPage_num(),request.getPage_size(),entityWrapper));
        pageBean.setTotal(articleMapper.listCount(entityWrapper));
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


    public void updateArticleStatus(Long id, short status){
        RedisClient.del(BlogRedisStaticKey.blog_home_list);
        articleMapper.updateArticleStatus(id,status);
    }

}
