package com.pro.admin.controller.develop.vo;

import com.common.api.entity.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guozhenhua
 * @date 2021/01/17
 */
@Getter
@Setter
public class DeployListReqVo extends PageRequest {

    String name;

}
