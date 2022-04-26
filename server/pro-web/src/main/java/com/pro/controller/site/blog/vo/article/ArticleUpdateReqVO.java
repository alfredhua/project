package com.pro.controller.site.blog.vo.article;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
@Getter
@Setter
public class ArticleUpdateReqVO {


  /**
   * id号
   */
  @NotNull(message = "id不能为空")
  private Long id;

  /**
   * 标题
   */
  @NotNull(message = "标题不能为空")
  private String title;

  /**
   * 文章分类
   */
  @NotNull(message = "文章分离不能为空")
  private String type;

  /**
   * 内容
   */
  @NotNull(message = "内容不能为空")
  private String context;

}
