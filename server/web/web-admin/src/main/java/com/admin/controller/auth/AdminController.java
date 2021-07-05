package com.admin.controller.auth;

import com.admin.controller.auth.vo.admin.*;
import com.admin.controller.common.AdminBaseController;
import com.auth.constants.AuthConstant;
import com.auth.dto.entity.Admin;
import com.auth.dto.AdminListReqDTO;
import com.auth.dto.LoginAdminRespDTO;
import com.auth.service.AdminService;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.redis.RedisUtils;
import com.common.util.BeanCopyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.common.domain.constants.SysErrorCodeEnum.CONFIRM_PASSWORD_ERROR;

/**
 * @author hua
 *
 */
@Api(tags = "管理员管理")
@RestController
@RequestMapping(value = AuthUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class AdminController extends AdminBaseController {


    @Autowired
    AdminService adminService;

    @Autowired
    RedisUtils redisUtils;

    @ApiOperation(value="管理员创建")
    @RequestMapping(value = AuthUrl.CREATE_ADMIN)
    public void createAdmin(@RequestBody @Valid AdminCreateReqVo adminCreateReqVo,BindingResult result)throws Exception{
        Admin adminReqDTO = BeanCopyUtil.copy(adminCreateReqVo, Admin.class);
        adminService.createAdmin(adminReqDTO);
    }

    @ApiOperation(value="管理员修改")
    @RequestMapping(value = AuthUrl.UPDATE_ADMIN)
    public void updateAdmin (@RequestBody @Valid AdminUpdateReqVo adminUpdateReqVo, BindingResult result) throws Exception {
        Admin admin = BeanCopyUtil.copy(adminUpdateReqVo, Admin.class);
        adminService.updateAdmin(admin);
    }

    @ApiOperation(value="管理员列表")
    @RequestMapping(value=AuthUrl.LIST_ADMIN)
    public PageBean<AdminRespVo> listAdmin(@RequestBody @Valid AdminListReqVo adminListReqVO,BindingResult result)throws Exception{
        AdminListReqDTO adminListReqDTO = BeanCopyUtil.copy(adminListReqVO, AdminListReqDTO.class);
        return pageResultReturn(adminService.listAdminByPage(adminListReqDTO),AdminRespVo.class);
    }


    @ApiOperation(value="获取当前用户信息")
    @RequestMapping(value = AuthUrl.GET_ADMIN_TOKEN)
    public AdminRespVo getAdminInfo(){
        return resultReturn(getLoginAdminInfo(),AdminRespVo.class);
    }

    @ApiOperation(value="更改密码")
    @RequestMapping(value = AuthUrl.UPDATE_PASSWORD)
    public void updatePassword(@RequestBody @Valid UpdatePasswordReqVo resetAdminPasswordReq,BindingResult result)throws Exception{
        if(!resetAdminPasswordReq.getNew_password().equals(resetAdminPasswordReq.getConfirm_password())){
            throw ResultException.error(CONFIRM_PASSWORD_ERROR);
        }
        LoginAdminRespDTO loginAdminInfo = getLoginAdminInfo();
        adminService.updatePassword(loginAdminInfo.getId(),resetAdminPasswordReq.getOld_password(),resetAdminPasswordReq.getConfirm_password());
    }

    @ApiOperation(value="更改管理员自己信息")
    @RequestMapping(value = AuthUrl.UPDATE_ADMIN_INFO)
    public void updateAdminInfo(@RequestBody @Valid AdminInfoReqVo adminInfoReqVo,BindingResult result)throws Exception{
        LoginAdminRespDTO loginAdminResp= getLoginAdminInfo();
        adminService.updateAdminInfo(loginAdminResp.getId(), adminInfoReqVo.getPhone(), adminInfoReqVo.getEmail());
        loginAdminResp.setEmail( adminInfoReqVo.getEmail());
        loginAdminResp.setPhone( adminInfoReqVo.getPhone());
        redisUtils.objectSet(AuthConstant.ADMIN_INFO.getKey()+getToken(), AuthConstant.ADMIN_INFO.getTimeOut(), loginAdminResp);
    }

    @ApiOperation(value="密码重置")
    @RequestMapping(value = AuthUrl.RESET_PASSWORD)
    public  void resetAdminPassword(@RequestBody @Valid ResetAdminPasswordReq resetAdminPasswordReq, BindingResult result)throws Exception{
        adminService.resetAdminPassword(resetAdminPasswordReq.getId());
    }

    @ApiOperation(value="设置权限编码")
    @RequestMapping(value = AuthUrl.SET_AUTH_DATA)
    public void setAuthData(@RequestBody @Valid AdminSetAuthDataReq adminSetAuthDataReq,BindingResult result)throws Exception{
        adminService.setAuthData(adminSetAuthDataReq.getId(),adminSetAuthDataReq.getAuth_data_code());
    }

    @ApiOperation(value="根据id获取用户")
    @RequestMapping(value=AuthUrl.GET_ADMIN_BY_ID)
    public AdminRespVo getAdminById(@PathVariable("id")long id) throws Exception {
        Admin admin = adminService.getAdminById(id);
        return resultReturn(admin, AdminRespVo.class);
    }



    @ApiOperation(value="管理员冻结")
    @RequestMapping(value = AuthUrl.UPDATE_ACTIVE_ADMIN)
    public void updateActiveAdmin(@RequestBody @Valid AdminActiveReqVo adminActiveReqVO, BindingResult result) throws ResultException {
        adminService.updateActiveAdmin(adminActiveReqVO.getId(),adminActiveReqVO.getIsActive());
    }


}
