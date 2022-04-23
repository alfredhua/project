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
@Table(value = "m_website.site_partner")
public class Partner  extends BaseDomain {


    /**
     * id号
     */
     
   private Long id;
    
    /**
     * 合作伙伴名称
     */
     
   private String name;
    
    /**
     * 跳转地址
     */
     
   private String href;

  /**
   * 图片地址
   */
  private String pic_url;
    

}
