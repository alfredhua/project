package com.pro.api.entity.blog;

import com.common.api.entity.request.PageRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hua
 */
@Setter
@Getter
public class ArticleListReqDto extends PageRequest {


    private short status;


    private String type;


}
