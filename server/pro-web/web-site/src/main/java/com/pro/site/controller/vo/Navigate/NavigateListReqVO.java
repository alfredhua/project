package com.pro.site.controller.vo.Navigate;

import com.common.api.entity.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2021/08/23
 */
@Getter
@Setter
public class NavigateListReqVO extends PageRequest {

    /**
     * 一级分类
     */
    String one_type;

    /**
     * 二级分类
     */
    String two_type;

}
