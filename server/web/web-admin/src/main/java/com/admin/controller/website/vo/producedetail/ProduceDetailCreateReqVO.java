package com.admin.controller.website.vo.producedetail;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Getter
@Setter
public class ProduceDetailCreateReqVO {

    /**
     * id号
     */
    @NotNull(message = "produce_id不能为空")
    private Long produce_id;

    /**
     * 文章内容
     */
    @NotNull(message = "context不能为空")
    private String context;

}
