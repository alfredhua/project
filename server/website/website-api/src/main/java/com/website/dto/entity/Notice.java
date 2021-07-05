package com.website.dto.entity;

import com.common.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2018/12/20
 */
@Getter
@Setter
public class Notice extends BaseDomain {

    /**
     * id
     */
    private long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型
     */
    private String type;

    /**
     * 内容
     */
    private String context;

    /**
     * 是否发布,0:未发布,1发布
     */
    private Integer publish;


    /**
     * 排序
     */
    private Integer ordering;

    /**
     * 点击次数
     */
    private Integer click_count;

    /**
     * 发布时间
     */
    private LocalDateTime publish_time;


}
