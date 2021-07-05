package com.auth.dto.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hua
 */
@Data
public class MenuRole implements Serializable {

    private long id;
    private String name;
    private String auth_list;
    private String comment;
    private LocalDateTime create_time;
    private boolean status;

}
