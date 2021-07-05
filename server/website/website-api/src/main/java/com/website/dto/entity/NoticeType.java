package com.website.dto.entity;

import com.common.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2018/12/20
 */
@Getter
@Setter
public class NoticeType extends BaseDomain {

    /**
     * id
     */
    private long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;


    /**
     * 是否激活，1:激活，0:未激活
     */
    private short status;
}
