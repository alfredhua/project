package com.blog.dto;

import com.common.domain.request.PageRequest;
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
