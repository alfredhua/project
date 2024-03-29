package com.website.entity;

import com.common.api.entity.BaseDomain;
import com.common.mybatis.annotation.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Getter
@Setter
@Table(value = "m_website.site_setting")
public class Setting extends BaseDomain {


    /**
     * id号
     */
     
   private Long id;
    
    /**
     * 类型
     */
     
   private String type;
    
    /**
     * 名称
     */
     
   private String name;
    
    /**
     * 开启1，关闭0
     */
     
   private Short status;
      
    /**
     * 父节点id，默认是0
     */
     
   private Long partner_id;
    
    /**
     * 创建时间
     */
     
   private LocalDateTime create_time;
    
    /**
     * 更新时间
     */
     
   private LocalDateTime update_time;
    
    /**
     * 是否删除,0:未删除,1删除
     */
     
   private Short del;
      
}
