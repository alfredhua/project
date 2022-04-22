package com.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    public static boolean isValidPhone(String phone){
        Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8|][0-9]{9}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

}
