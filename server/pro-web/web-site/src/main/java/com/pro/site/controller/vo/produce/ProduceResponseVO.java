package com.pro.site.controller.vo.produce;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guozhenhua
 * @date 2019/07/14
 */
@Getter
@Setter
public class ProduceResponseVO {

    private Long id;

    private String title;

    private String file_list;

    private Short pc_show;

    private Short m_show;

    private Short home_show;

    private String cover_image;

    private String introduce;

    private Integer ordering;

    private LocalDateTime create_time;

    private String context;

}
