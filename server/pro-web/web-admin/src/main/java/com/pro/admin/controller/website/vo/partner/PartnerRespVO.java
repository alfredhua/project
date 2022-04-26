package com.pro.admin.controller.website.vo.partner;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Getter
@Setter
public class PartnerRespVO {


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

  /**
<<<<<<< HEAD
   * pc是否展示，0不展示，1展示
   */

  private Short pc_show;

  /**
   * 手机站是否展示，0不展示，1展示
   */

  private Short m_show;

  /**
   * 排序
   */

  private Integer ordering;

  /**
=======
>>>>>>> dev
   * 是否删除
   */

  private Short del;

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


  private String auth_data_code;

}
