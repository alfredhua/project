package com.admin.controller.website.vo.noticetype;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NoticeTypeActiveRequest {

  /**
   * id
   */
  @NotNull(message = "id不能为空")
  private long id;

  /**
   * 操作不能为空
   */
  @NotNull(message = "操作不能为空")
  private short status;


}
