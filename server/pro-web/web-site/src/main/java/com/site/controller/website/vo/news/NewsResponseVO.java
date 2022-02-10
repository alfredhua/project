package com.site.controller.website.vo.news;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guozhenhua
 * @date 2019/07/14
 */
@Getter
@Setter
public class NewsResponseVO {


    private Long id;

    private String title;

    private String source;

    private String source_url;

    private String pic;

    private String introduce;

    private String context;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime create_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime update_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime publish_time;

    private Short del;
}
