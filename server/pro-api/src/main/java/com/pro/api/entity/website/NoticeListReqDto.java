package com.pro.api.entity.website;

import com.common.api.entity.request.PageRequest;
import lombok.Data;

@Data
public class NoticeListReqDto extends PageRequest {

    String type;

}
