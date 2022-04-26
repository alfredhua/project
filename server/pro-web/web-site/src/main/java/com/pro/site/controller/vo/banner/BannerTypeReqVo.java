package com.pro.site.controller.vo.banner;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2018/11/14
 */
@Getter
@Setter
public class BannerTypeReqVo {

    @NotNull(message = "type不能为空")
    private String type;

}
