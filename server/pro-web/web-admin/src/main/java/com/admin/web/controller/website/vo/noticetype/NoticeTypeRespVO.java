package com.admin.web.controller.website.vo.noticetype;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2018/12/21
 */
@Getter
@Setter
public class NoticeTypeRespVO {

    /**
     * id
     */
    private long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_time;

    /**
     * 更新时间
     */
    private LocalDateTime update_time;

    /**
     * 是否删除，1:删除，0:未删除
     */
    private short del;
    /**
     * 是否激活，1:激活，0:未激活
     */
    private short status;

    /**
     * 权限码
     */
    private String auth_data_code;

}
