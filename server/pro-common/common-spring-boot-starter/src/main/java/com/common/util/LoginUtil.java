package com.common.util;

import com.common.domain.entity.UserInfo;

public class LoginUtil {

    private static ThreadLocal<UserInfo> local=new ThreadLocal();


    public static UserInfo getLoginUser(){
        return local.get();
    }

    public static void remove(){
        local.remove();
    }

    public static void initLoginUser(UserInfo userInfo) {
        local.set(userInfo);
    }

}
