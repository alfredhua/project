package com.common.api.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class LoginUserInfo {

    private Long id;
    private String token;
    private String user_name;
    private LocalDateTime create_time;
    private String role_id_list;
    private String phone;
    private String email;
    private short update_password;
    private short status;
    // 功能权限
    private Set<String> auth_list;
    //数据权限
    private List<String> auth_data_code_list;
}
