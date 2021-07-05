package com.site.controller.blog.vo.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
@Getter
@Setter
public class ArticleRespVO {

    private Long id;

    private String title;

    private String type;

    private Short reprint;

    private String introduce;

    private Integer click_count;

    private short content_type;

    private Integer like_count;

    private String context;

    private String pic_url;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime create_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime update_time;

    private Short del;
}
