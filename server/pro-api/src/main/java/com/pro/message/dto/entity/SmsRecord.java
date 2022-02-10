package com.pro.message.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SmsRecord {


    private long id;
    private String numbers;
    private String content;
    private LocalDateTime receive_time;
    private short status;
    private String result;
    private String template_code;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private short channel_type;

}
