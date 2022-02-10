package com.pro.auth.dto;

import com.common.domain.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminListReqDTO extends PageRequest {

    private String phone;
    private String user_name;
}