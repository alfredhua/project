package com.admin.web.controller.website.vo.article;

import com.common.domain.request.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * @auth guozhenhua
 * @date 2018/11/10
 */
@Api
@Getter
@Setter
public class ArticleListReqVo extends PageRequest {

    @ApiModelProperty(value = "文章类型")
    String type;


}
