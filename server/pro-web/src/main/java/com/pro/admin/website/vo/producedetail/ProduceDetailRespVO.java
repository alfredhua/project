package com.pro.admin.website.vo.producedetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Api
@Getter
@Setter
public class ProduceDetailRespVO {

    @ApiModelProperty(value = "id号")
    private Long id;

    @ApiModelProperty(value = "产品id号")
    private Long produce_id;

    @ApiModelProperty(value = "内容")
    private String context;

}
