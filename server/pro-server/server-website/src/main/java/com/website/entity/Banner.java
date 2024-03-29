package com.website.entity;

import com.common.api.entity.BaseDomain;
import com.common.mybatis.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2018/11/10
 */
@Getter
@Setter
@Table(value = "m_website.site_banner")
public class Banner extends BaseDomain {
    /**
     * id
     */
    private Long id;
    /**
     * 地址
     */
    private String url;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String type;

    /**
     * 跳转地址
     */
    private String href;

    /**
     * 是否开启
     */
    private Short enable;

    /**
     * 排序
     */
    private Integer order;




}
