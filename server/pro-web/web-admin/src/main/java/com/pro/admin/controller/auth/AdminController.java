package com.pro.admin.controller.auth;

import com.auth.entity.Admin;
import com.auth.service.AdminService;
import com.common.api.constants.RedisConstant;
import com.common.api.constants.SysErrorCodeEnum;
import com.common.api.entity.LoginUserInfo;
import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.api.exception.ResultException;
import com.common.redis.client.RedisClient;
import com.common.util.BeanCopyUtil;
import com.common.util.LoginUtil;
import com.pro.admin.controller.auth.vo.admin.*;
import com.pro.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.common.api.constants.SysErrorCodeEnum.CONFIRM_PASSWORD_ERROR;

/**
 * @author hua
 *
 */
@Api(tags = "管理员管理")
@RestController
@RequestMapping(value = AuthUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class AdminController extends BaseController {

    @Autowired
    AdminService adminService;

    @ApiOperation(value="管理员列表")
    @RequestMapping(value=AuthUrl.LIST_ADMIN)
    public PageBean<AdminRespVo> listAdmin(@RequestBody @Valid AdminListReqVo adminListReqVO )throws Exception{
        return pageResultReturn(adminService.listAdminByPage(new PageRequest(adminListReqVO.getPage_num(),adminListReqVO.getPage_size())
                ),AdminRespVo.class);
    }


    @ApiOperation(value="管理员创建")
    @RequestMapping(value = AuthUrl.CREATE_ADMIN)
    public void createAdmin(@RequestBody @Valid AdminCreateReqVo adminCreateReqVo )throws Exception{
        adminService.createAdmin(
                BeanCopyUtil.copy(adminCreateReqVo, Admin.class),
                adminCreateReqVo.getAuth_code_list()
        );
    }

    @ApiOperation(value="管理员修改")
    @RequestMapping(value = AuthUrl.UPDATE_ADMIN)
    public void updateAdmin (@RequestBody @Valid AdminUpdateReqVo adminUpdateReqVo ) throws Exception {
        adminService.updateAdmin(
                BeanCopyUtil.copy(adminUpdateReqVo, Admin.class),
                adminUpdateReqVo.getAuth_code_list()
        );
    }

    @ApiOperation(value="获取当前用户信息")
    @RequestMapping(value = AuthUrl.GET_ADMIN_TOKEN)
    public AdminRespVo getAdminInfo(@RequestBody AdminTokenReqVo adminTokenReqVo) throws ResultException {
        //首次登录的时候不能走filter，所以loginUtil没有信息
        if (ObjectUtils.isEmpty(adminTokenReqVo.getToken())){
            throw ResultException.error(SysErrorCodeEnum.TOKEN_INVALID);
        }
        return resultReturn(RedisClient.objectGet(RedisConstant.ADMIN_INFO.getKey()+adminTokenReqVo.getToken()),AdminRespVo.class);
    }

    @ApiOperation(value="更改密码")
    @RequestMapping(value = AuthUrl.UPDATE_PASSWORD)
    public void updatePassword(@RequestBody @Valid UpdatePasswordReqVo resetAdminPasswordReq )throws Exception{
        if(!resetAdminPasswordReq.getNew_password().equals(resetAdminPasswordReq.getConfirm_password())){
            throw ResultException.error(CONFIRM_PASSWORD_ERROR);
        }
        adminService.updatePassword(LoginUtil.getLoginUser().getId(),resetAdminPasswordReq.getOld_password(),resetAdminPasswordReq.getConfirm_password());
    }

    @ApiOperation(value="更改管理员自己信息")
    @RequestMapping(value = AuthUrl.UPDATE_ADMIN_INFO)
    public void updateAdminInfo(@RequestBody @Valid AdminInfoReqVo adminInfoReqVo )throws Exception{
        LoginUserInfo loginUser = LoginUtil.getLoginUser();
        Admin admin=new Admin();
        admin.setId(loginUser.getId());
        admin.setEmail(adminInfoReqVo.getEmail());
        admin.setPhone(adminInfoReqVo.getPhone());
        adminService.updateAdminInfo(admin);
        loginUser.setEmail( adminInfoReqVo.getEmail());
        loginUser.setPhone( adminInfoReqVo.getPhone());
        RedisClient.objectSet(RedisConstant.ADMIN_INFO.getKey()+loginUser.getToken(), RedisConstant.ADMIN_INFO.getTimeOut(), loginUser);
    }

    @ApiOperation(value="密码重置")
    @RequestMapping(value = AuthUrl.RESET_PASSWORD)
    public  void resetAdminPassword(@RequestBody @Valid ResetAdminPasswordReq resetAdminPasswordReq )throws Exception{
        adminService.resetAdminPassword(resetAdminPasswordReq.getId());
    }

    @ApiOperation(value="设置权限编码")
    @RequestMapping(value = AuthUrl.SET_AUTH_DATA)
    public void setAuthData(@RequestBody @Valid AdminSetAuthDataReq adminSetAuthDataReq )throws Exception{
//        adminService.setAuthData(adminSetAuthDataReq.getId(),adminSetAuthDataReq.getAuth_data_code());
    }

    @ApiOperation(value="根据id获取用户")
    @RequestMapping(value=AuthUrl.GET_ADMIN_BY_ID)
    public AdminRespVo getAdminById(@PathVariable("id")long id) throws Exception {
        Admin admin = adminService.getAdminById(id);
        return resultReturn(admin, AdminRespVo.class);
    }

    @ApiOperation(value="管理员冻结")
    @RequestMapping(value = AuthUrl.UPDATE_ACTIVE_ADMIN)
    public void updateActiveAdmin(@RequestBody @Valid AdminActiveReqVo adminActiveReqVO ) {
        adminService.updateActiveAdmin(adminActiveReqVO.getId(),adminActiveReqVO.getIsActive());
    }

}
