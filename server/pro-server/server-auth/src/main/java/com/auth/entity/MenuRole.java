package com.auth.entity;

import com.common.mybatis.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hua
 */
@Data
@Table(value = "m_auth.auth_role")
public class MenuRole implements Serializable {

    private long id;
    private String name;
    private String auth_list;
    private String comment;
    private LocalDateTime create_time;
    private boolean status;

}
