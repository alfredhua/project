package com.pro.controller.admin.website.vo.banner;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2018/11/10
 */
@Getter
@Setter
public class BannerListRespVo implements Serializable {


    @ApiModelProperty(value = "id号")
    private long id;

    @ApiModelProperty(value = "图片上传的url")
    private String url;

    @ApiModelProperty(value = "图片名称")
    private String name;

    @ApiModelProperty(value = "图片类型")
    private String type;

    @ApiModelProperty(value = "跳转地址")
    private String href;

    @ApiModelProperty(value = "是否开启")
    private short enable;

    @ApiModelProperty(value = "排序")
    private Integer order;

    @ApiModelProperty(value = "是否删除")
    private short deleted;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private LocalDateTime update_time;



}
