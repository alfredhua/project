package com.pro.api.entity.blog;

import com.common.api.entity.request.PageRequest;
import lombok.Data;

@Data
public class ArticleListReqDto extends PageRequest {


    private short status;


}
