package com.auth.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guozhenhua
 * @date 2021/05/24
 */
@Setter
@Getter
public class AuthDataAdmin {

    private Long id;

    private Long admin_id;

    private String data_code;

    private LocalDateTime create_time;

    private LocalDateTime update_time;


    private short del;

}
