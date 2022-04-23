package com.pro.api.auth;

import com.common.api.entity.request.PageRequest;
import lombok.Data;

@Data
public class DeployListReqDto extends PageRequest {

    String name;
}
