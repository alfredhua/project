package com.admin.controller.website.vo.setting;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author guozhenhua
 * @date 2019/07/11
 */
@Getter
@Setter
public class UpdateStatusRequestVO {

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    private long id;

    /**
     * 操作不能为空
     */
    @NotNull(message = "status不能为空")
    private short status;
}
