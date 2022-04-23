package com.auth.entity;

import com.common.mybatis.annotation.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hua
 *
 */
@Getter
@Setter
@Table(value = "m_auth.auth_admin")
public class Admin implements Serializable {

    private long id;
    private String user_name;
    private LocalDateTime create_time;
    private String role_id_list;
    private String phone;
    private String email;
    private String password;
    private short update_password;
    private short status;

}
