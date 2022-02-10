package com.pro.website.dto.entity;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2019/07/12
 */
@Getter
@Setter
public class News  implements Serializable {


  /**
   * id号
   */

  private Long id;

  /**
   * 标题
   */

  private String title;

  /**
   * 是否发布
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

  private LocalDateTime create_time;

  /**
   * 更新时间
   */

  private LocalDateTime update_time;


  /**
   * 更新时间
   */
  private LocalDateTime publish_time;

  /**
   * 是否删除
   */

  private Short del;

  /**
   * 数据权限码
   */
  private String auth_data_code;

}
