package com.develop.entity;

import com.common.mybatis.annotation.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guozhenhua
 * @date 2021/01/17
 */
@Getter
@Setter
@Table(value = "m_develop.de_deploy")
public class Deploy {

    private long id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点值
     */
    private String name_value;

    /**
     * 默认值
     */
    private String default_value;

    /**
     * 描述
     */
    private String description;

    /**
     * 操作人
     */
    private String operator;

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
