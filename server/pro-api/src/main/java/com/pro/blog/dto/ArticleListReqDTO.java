package com.pro.blog.dto;

import com.common.domain.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
@Getter
@Setter
public class ArticleListReqDTO extends PageRequest {

    String type;

    Short status;

}
