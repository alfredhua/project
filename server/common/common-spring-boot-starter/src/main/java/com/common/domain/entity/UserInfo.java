package com.common.domain.entity;

import com.common.domain.constants.SourceEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfo {

    private String user_name;
    private LocalDateTime create_time;
    private String role_id_list;
    private String phone;
    private String email;
    private short update_password;
    private short status;
    private SourceEnum sourceEnum;

}
