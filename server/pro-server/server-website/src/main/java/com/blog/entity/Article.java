package com.blog.entity;

import com.common.mybatis.annotation.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
@Getter
@Setter
@Table(value = "m_website.blog_article")
public class Article  implements Serializable {

  /**
   * id号
   */
  private Long id;

  /**
   * 标题
   */
  private String title;

  /**
   * 文章分类
   */
  private String type;


  private short reprint;


  private short content_type;


  private String introduce;


  /**
   * 点击数
   */
  private Integer click_count;

  private Integer like_count;

  /**
   * 内容
   */
  private String context;


  /**
   * 图片封面url
   */
  private String pic_url;

  /**
   * 1:待发布，2:已发布，
   */

  private Short status;

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
