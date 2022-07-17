package com.pro.api.entity.website;

import com.common.api.entity.request.PageRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeListReqDto extends PageRequest {

    String type;

}
