package com.pro.admin.auth.vo.admin;

import com.common.api.entity.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hua
 */
@Getter
@Setter
public class AdminListReqVo extends PageRequest {

    private String phone;
    private String userName;
}
