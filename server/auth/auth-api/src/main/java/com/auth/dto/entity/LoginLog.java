package com.auth.dto.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hua
 */
@Data
public class LoginLog implements Serializable {

    private String id;
    private long adminId;
    private String ipAddress;
    private LocalDateTime createTime;
    private String source;

}
