package com.admin.web.controller.website.vo.produce;

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
public class ProduceRespVO {

    /**
     * id号
     */

    private Long id;

    /**
     * 产品id号
     */

    private String title;

    /**
     * 文件列表
     */
    private String file_list;

    /**
     * pc是否展示，0不展示，1展示
     */

    private Short pc_show;

    /**
     * 手机站是否展示，0不展示，1展示
     */

    private Short m_show;

    /**
     * 首页是否展示，0不展示，1展示
     */

    private Short home_show;

    /**
     * 封面
     */

    private String cover_image;

    /**
     * 排序
     */

    private Integer ordering;

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
     * 简介
     */
    private String introduce;

    /**
     * 内容
     */
    private String context;

    /**
     * 数据权限码
     */
    private String auth_data_code;

}
