package com.pro.auth.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guozhenhua
 * @date 2021/05/23
 */
@Setter
@Getter
public class AuthData {

    private Long id;

    private String name;

    private String code;

    private LocalDateTime create_time;

    private LocalDateTime update_time;

    private short del;

}
