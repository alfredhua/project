package com.pro.auth.dto;

import com.pro.auth.dto.entity.Admin;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @auth guozhenhua
 * @date 2018/11/06
 */
@Getter
@Setter
public class LoginAdminRespDTO extends Admin {

    private Set<String> auth_list;

}
