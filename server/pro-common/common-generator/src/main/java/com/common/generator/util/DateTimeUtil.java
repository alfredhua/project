package com.common.generator.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @auth guozhenhua
 * @date 2018/12/24
 */
public class DateTimeUtil {

    public  static String   getDate(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
