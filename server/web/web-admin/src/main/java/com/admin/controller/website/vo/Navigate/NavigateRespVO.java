package com.admin.controller.website.vo.Navigate;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
/**
 * @auth guozhenhua
 * @date 2021/08/23
 */
@Getter
@Setter
public class NavigateRespVO {

             /**
     * id号
     */
    private Long id;
             
  /**
   * 分类
   */
   private String type;
           
  /**
   * 标题
   */
   private String title;
           
  /**
   * 封面url
   */
   private String icon;
           
  /**
   * 简介
   */
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
