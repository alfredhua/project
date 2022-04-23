package com.pro.blog.dto;

import com.common.api.entity.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
@Getter
@Setter
public class TypeListReqDTO extends PageRequest {

    String type;

}
