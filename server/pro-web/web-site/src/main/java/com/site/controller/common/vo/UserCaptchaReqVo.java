package com.site.controller.common.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2018/11/14
 */
@Getter
@Setter
public class UserCaptchaReqVo {

    @NotNull(message = "验证码不能为空")
    private String pic_verify;


}
