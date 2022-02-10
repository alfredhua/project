package com.site.controller.website.vo.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2018/12/27
 */
@Getter
@Setter
public class NoticeRespVo implements Serializable {

    private long id;

    private String title;

    private String type;

    private String context;

    private Integer publish;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime create_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime update_time;

    private Short del;

    private Integer ordering;

    private Integer click_count;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime publish_time;

}
