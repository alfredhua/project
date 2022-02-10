package com.pro.auth.dto.entity;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hua
 *
 */
@Getter
@Setter
public class Admin implements Serializable {

    private long id;
    private String user_name;
    private LocalDateTime create_time;
    private String auth_data_name;
    private String role_id_list;
    private String phone;
    private String email;
    private String password;
    private short update_password;
    private short status;

    /**
     * 数据权限编码
     */
    private List<String> auth_code_list;

}
