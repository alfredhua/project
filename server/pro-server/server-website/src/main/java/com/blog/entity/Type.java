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
@Table(value = "m_website.blog_type")
public class Type  implements Serializable {

    /**
     * id号
     */
    private Long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 类型名称
     */
    private String name;

    private String introduce;

    /**
     * 是否激活:0未激活,1:激活
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
