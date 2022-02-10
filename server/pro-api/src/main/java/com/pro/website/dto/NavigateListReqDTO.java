package com.pro.website.dto;

import lombok.Getter;
import lombok.Setter;
import com.common.domain.request.PageRequest;
/**
 * @auth guozhenhua
 * @date 2021/08/23
 */
@Getter
@Setter
public class NavigateListReqDTO extends PageRequest {

    /**
     * 一级分类
     */
    String one_type;

    /**
     * 二级分类
     */
    String two_type;

}
