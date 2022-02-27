package com.admin.web.controller.auth.vo.role;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auth guozhenhua
 * @date 2018/11/05
 */

@Getter
@Setter
public class RoleStatusReqVo implements Serializable {

    @NotNull(message = "id号不能为空")
    private long id;

    /**
     * 1：禁用，0:激活
     */
    @NotNull(message = "状态不能为空")
    private boolean status;
}
