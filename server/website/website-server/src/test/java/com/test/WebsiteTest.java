package com.test;

import com.WebsiteCore;
import com.blog.dto.entity.Article;
import com.blog.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author guozhenhua
 * @date 2020/08/23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WebsiteCore.class)
public class WebsiteTest {


    @Autowired
    ArticleService articleService;

    @Test
    public void test() throws Exception {
        List<Article> a=articleService.listArticleHome();
        System.out.println(a);
    }

}
