package com.pro.api.entity.setting;

import lombok.Data;

import java.util.List;

@Data
public class SettingRespDto {

    /**
     * id号
     */

    private Long id;

    /**
     * 类型
     */

    private String type;

    /**
     * 名称
     */

    private String name;

    /**
     * 开启1，关闭0
     */

    private Short status;

    /**
     * 父节点id，默认是0
     */

    private Long partner_id;


    /**
     * 子节点
     */
    private List<SettingRespDto> children;
}
