package com.website.entity;

import com.common.api.entity.BaseDomain;
import com.common.mybatis.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Getter
@Setter
@Table(value = "m_website.site_produce")
public class Produce extends BaseDomain {


    /**
     * id号
     */
     
   private Long id;
    
    /**
     * 产品id号
     */
     
   private String title;
    
    /**
     * 产品图片
     */
     
   private String file_list;
      
    /**
     * pc是否展示，0不展示，1展示
     */
     
   private Short pc_show;
      
    /**
     * 手机站是否展示，0不展示，1展示
     */
     
   private Short m_show;
      
    /**
     * 首页是否展示，0不展示，1展示
     */
     
   private Short home_show;

    /**
     * 封面图片
     */
    private String cover_image;

    /**
     * 排序
     */
     
   private Integer ordering;


   private String context;
      
   private String introduce;
      
}
