package com.test;

import com.WebsiteCore;
import com.blog.constant.NavigateOneTypeEnum;
import com.blog.dto.entity.Article;
import com.blog.service.ArticleService;
import com.common.util.IDGenerate;
import com.website.dto.entity.Navigate;
import com.website.service.NavigateService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.time.LocalDateTime;
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



    @Autowired
    NavigateService navigateService;

    @Test
    public void readFile(){
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("manual.txt").getPath();
            FileReader fr = new FileReader(path);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while ((line=br.readLine())!=null){
                String[] split =line.split("\\|");
                Navigate navigate=new Navigate();
                navigate.setOne_type(NavigateOneTypeEnum.MANUAL.getType());
                navigate.setTwo_type(split[1]);
                navigate.setIcon(split[2]);
                navigate.setHref(split[3]);
                navigate.setTitle(split[4]);
                String introduce=null;
                try{
                    introduce=split[5];
                }catch (Exception e){
                    introduce=null;
                }
                navigateService.createNavigate(navigate);
            }
            br.close();
            fr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
