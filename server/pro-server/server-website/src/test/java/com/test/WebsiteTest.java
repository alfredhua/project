package com.test;

import com.WebsiteCore;
import com.blog.constant.NavigateOneTypeEnum;
import com.pro.website.dto.entity.Navigate;
import com.website.service.NavigateService;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guozhenhua
 * @date 2020/08/23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WebsiteCore.class)
public class WebsiteTest {

    static Map<NavigateOneTypeEnum,String> map=new HashMap<>();
    static {
        map.put(NavigateOneTypeEnum.BOOK,"book.txt");
        map.put(NavigateOneTypeEnum.INDEX,"index.txt");
        map.put(NavigateOneTypeEnum.MANUAL,"manual.txt");
    }
    @Autowired
    NavigateService navigateService;

    @Test
    public void insert(){
        NavigateOneTypeEnum navigateOneTypeEnum = NavigateOneTypeEnum.MANUAL;
        List<String> list = readFile(navigateOneTypeEnum);
        list.forEach(line->{
            Navigate navigate=new Navigate();
            String[] split =line.split("\\|");
            navigate.setOne_type(navigateOneTypeEnum.getType());
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
            navigate.setIntroduce(introduce);
            navigateService.createNavigate(navigate);
        });
    }

    @Test
    public void update(){
        NavigateOneTypeEnum navigateOneTypeEnum = NavigateOneTypeEnum.MANUAL;
        List<String> list = readFile(navigateOneTypeEnum);
        list.forEach(line->{
            String[] split =line.split("\\|");
                Navigate navigate=new Navigate();
                navigate.setTitle(split[4]);
                String introduce=null;
                try{
                    introduce=split[5];
                }catch (Exception e){
                    introduce=null;
                }
                navigate.setIntroduce(introduce);
                navigateService.updateNavigate(navigate);
        });
    }

    private List<String> readFile(NavigateOneTypeEnum navigateOneTypeEnum){
        List<String> list=new ArrayList<>();
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource(map.get(navigateOneTypeEnum)).getPath();
            FileReader fr = new FileReader(path);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while ((line=br.readLine())!=null){
                list.add(line);
            }
            br.close();
            fr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
