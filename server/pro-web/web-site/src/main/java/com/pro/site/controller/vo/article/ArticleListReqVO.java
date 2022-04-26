package com.pro.site.controller.vo.article;

import com.common.api.entity.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
@Getter
@Setter
public class ArticleListReqVO extends PageRequest {

    String type;

}
