package com.auth.entity;

import com.common.mybatis.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hua
 */
@Data
@Table(value = "m_auth.auth_login_log")
public class LoginLog implements Serializable {

    private String id;
    private long admin_id;
    private String ip_address;
    private LocalDateTime create_time;

}
