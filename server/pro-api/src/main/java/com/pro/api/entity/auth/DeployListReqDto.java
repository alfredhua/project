package com.pro.api.entity.auth;

import com.common.api.entity.request.PageRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeployListReqDto extends PageRequest {

    String name;
}
