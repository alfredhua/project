package com.pro.api.entity.auth;

import com.common.api.entity.request.PageRequest;
import lombok.Data;

@Data
public class DeployListReqDto extends PageRequest {

    String name;
}
