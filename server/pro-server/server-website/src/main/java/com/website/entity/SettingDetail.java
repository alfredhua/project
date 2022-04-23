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
@Table(value = "m_website.site_setting_detail")
public class SettingDetail  extends BaseDomain{


    /**
     * id号
     */
     
   private Long id;
    
    /**
     * 类型
     */
     
   private String type;
    
    /**
     * 内容
     */
     
   private String content;


   private String url;

   private String introduce;


}
