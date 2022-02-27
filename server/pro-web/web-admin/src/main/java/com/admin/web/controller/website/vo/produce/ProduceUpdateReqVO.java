package com.admin.web.controller.website.vo.produce;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Api
@Getter
@Setter
public class ProduceUpdateReqVO {

    @ApiModelProperty(value = "id号")
    @NotNull(message = "id不能为空")
    private long id;

    @ApiModelProperty(value = "标题")
    @NotNull(message = "title不能为空")
    private String title;

    @ApiModelProperty(value = "产品图片")
    @NotNull(message = "file_list不能为空")
    private String file_list;

    @ApiModelProperty(value = "pc是否展示，0不展示，1展示")
    @NotNull(message = "pc_show不能为空")
    private Short pc_show;

    @ApiModelProperty(value = "手机站是否展示，0不展示，1展示")
    @NotNull(message = "m_show不能为空")
    private Short m_show;

    @ApiModelProperty(value = "首页是否展示，0不展示，1展示")
    @NotNull(message = "home_show不能为空")
    private Short home_show;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "ordering不能为空")
    private Integer ordering;

    @ApiModelProperty(value = "封面图片")
    @NotNull(message = "cover_image不能为空")
    private String cover_image;

    @ApiModelProperty(value = "简介")
    @NotNull(message = "introduce不能为空")
    private String introduce;

    @ApiModelProperty(value = "数据权限码")
    @NotNull(message = "数据权限不能为空")
    private String auth_data_code;

}
