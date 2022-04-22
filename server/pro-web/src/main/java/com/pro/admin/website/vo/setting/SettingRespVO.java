package com.pro.admin.website.vo.setting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Api
@Getter
@Setter
public class SettingRespVO {

    @ApiModelProperty(value = "key")
    private long key;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "状态")
    private short status;

    @ApiModelProperty(value = "描述")
    private short desc;

    @ApiModelProperty(value = "产品id")
    private long partner_id;

    @ApiModelProperty(value = "子类型")
    private List<SettingRespVO> children;


}
