package com.admin.web.controller.website.vo.news;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2019/07/12
 */
@Getter
@Setter
public class NewsRespVO {

  /**
   * id号
   */
  private Long id;

  /**
   * 标题
   */

  private String title;

  /**
   * 是否开启
   */

  private Short publish;

  /**
   * 来源
   */
  private String source;

  /**
   * 来源地址
   */

  private String source_url;

  /**
   * 列表预览图片
   */

  private String pic;

  /**
   * 描述
   */

  private String introduce;

  /**
   * 内容
   */

  private String context;

  /**
   * 创建时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  private LocalDateTime create_time;

  /**
   * 更新时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  private LocalDateTime update_time;

  /**
   * 更新时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  private LocalDateTime publish_time;


  /**
   * 是否删除
   */

  private Short del;

  private String auth_data_code;

}
