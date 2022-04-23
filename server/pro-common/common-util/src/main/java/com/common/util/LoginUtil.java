package com.common.util;

import com.common.api.entity.LoginUserInfo;

public class LoginUtil {

    private static ThreadLocal<LoginUserInfo> local=new ThreadLocal();


    public static LoginUserInfo getLoginUser(){
        return local.get();
    }

    public static void remove(){
        local.remove();
    }

    public static void initLoginUser(LoginUserInfo userInfo) {
        local.set(userInfo);
    }

}