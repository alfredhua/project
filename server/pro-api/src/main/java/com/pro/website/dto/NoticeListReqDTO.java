package com.pro.website.dto;

import com.common.domain.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @auth guozhenhua
 * @date 2018/12/25
 */
@Getter
@Setter
public class NoticeListReqDTO extends PageRequest {

  String type;

}
