package com.auth.entity;

import com.common.mybatis.annotation.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guozhenhua
 * @date 2021/05/23
 */
@Setter
@Getter
@Table(value = "m_auth.auth_data")
public class AuthData {

    private Long id;

    private String name;

    private String code;

    private LocalDateTime create_time;

    private LocalDateTime update_time;

    private short del;

}
