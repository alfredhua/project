package com.pro.admin.auth;

/**
 * GET-获取
 * UPDATE-更新
 * SAVE-保存
 * DEL-删除
 * @author hua
 */
public class AuthUrl {

    /**
     * 访问根节点
     */
    public static final String BASE_URL="/admin/auth";

    public static final String SAVE_AUTH_DATA="/save-auth-data"; //用户权限
    public static final String LIST_AUTH_DATA="/list-auth-data"; //数据权限列表
    public static final String LIST_AUTH_DATA_ALL="/list-auth-data-all"; //数据权限



    public static final String CREATE_ADMIN="/create-admin"; //用户保存
    public static final String UPDATE_ADMIN="/update-admin"; //更新
    public static final String LIST_ADMIN="/list-admin";   //用户列表

    /**
     * 获取当前登录用户信息
     */
    public static final String GET_ADMIN_TOKEN="/get-admin-by-token";


    /**
     * 重置密码
     */
    public static final String RESET_PASSWORD="/reset-password";

    public static final String SET_AUTH_DATA="/set-auth-data";
    /**
     * 重置密码
     */
    public static final String UPDATE_PASSWORD="/update-password";

    /**
     * 自己修改信息
     */
    public static final String UPDATE_ADMIN_INFO="/update-admin-info";

    /**
     * 根据id获取用户
     */
    public static final String GET_ADMIN_BY_ID="/get-admin-by-id/{id}";

    /**
     * 冻结用户
     */
    public static final String UPDATE_ACTIVE_ADMIN="/update-active-admin";

    /**
     *角色列表
     */
    public static final  String LIST_ROLE="/list-role";

    /**
     * 角色保存
     */
    public static final String CREATE_ROLE="/create-role";

    /**
     * 根据id更新角色信息
     */
    public static final String UPDATE_ROLE="/update-role";

    /**
     * 获取全部可用的角色
     */
    public static final String LIST_ALL_USE_ROLE="/list-all-use-role";

    /**
     * 获取全部可用的角色
     */
    public static final String UPDATE_ROLE_STATUS="/update-role-status";

    /**
     * 根据id获取角色
     */
    public static final String GET_ROLE_BY_ID ="/get-role-by-id/{id}" ;

    /**
     * 登录
     */
    public static final String LOGIN = "/login";

    /**
     * 验证是否登录
     */
    public static final String CHECK_LOGIN = "/check-login";
    /**
     * 登出
     */
    public static final String LOGOUT = "/logout";

}
