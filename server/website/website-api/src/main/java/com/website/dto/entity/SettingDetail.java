package com.website.dto.entity;

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
public class SettingDetail  implements Serializable {


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
