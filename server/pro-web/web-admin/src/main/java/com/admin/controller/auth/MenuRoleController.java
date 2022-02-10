package com.admin.controller.auth;

import com.admin.controller.auth.vo.role.*;
import com.admin.controller.common.AdminBaseController;
import com.auth.service.MenuRoleService;
import com.common.aspect.annotation.LimitTime;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.auth.dto.MenuRoleListReqDTO;
import com.pro.auth.dto.entity.MenuRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author hua
 *
 */
@Api(tags = "菜单角色管理")
@RestController
@RequestMapping(value = AuthUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class MenuRoleController extends AdminBaseController {


    @Autowired
    MenuRoleService menuRoleService;

    @ApiOperation(value="角色保存")
    @RequestMapping(value = AuthUrl.CREATE_ROLE)
    public void createRole(@RequestBody @Valid RoleReqVO roleReqVO, BindingResult result){
        menuRoleService.createRole(BeanCopyUtil.copy(roleReqVO, MenuRole.class));
    }

    @ApiOperation(value="角色列表")
    @RequestMapping(value = AuthUrl.LIST_ROLE)
    public PageBean<RoleRespVo> listRole(@RequestBody @Valid RoleListReqVo roleListReqVo, BindingResult result){
        return pageResultReturn(menuRoleService.listRole(BeanCopyUtil.copy(roleListReqVo, MenuRoleListReqDTO.class)), RoleRespVo.class);
    }

    @ApiOperation(value="根据id更新角色信息")
    @RequestMapping(value=AuthUrl.UPDATE_ROLE)
    public void updateRole(@RequestBody @Valid RoleUpdateReqVO roleUpdateReqVO, BindingResult result) throws Exception {
        menuRoleService.updateRole(BeanCopyUtil.copy(roleUpdateReqVO, MenuRole.class));
    }

    @ApiOperation(value="根据id更新角色信息")
    @RequestMapping(value=AuthUrl.GET_ROLE_BY_ID)
    public RoleRespVo getRoleById(@PathVariable("id") long id){
        return resultReturn(menuRoleService.getRoleById(id),RoleRespVo.class);
    }

    @ApiOperation(value="获取全部可用的角色")
    @RequestMapping(value = AuthUrl.LIST_ALL_USE_ROLE)
    public List<RoleRespVo> listAllUseRole(){
        return listReturn(menuRoleService.listAllUseRole(),RoleRespVo.class);
    }

    @ApiOperation(value="更新用户角色状态")
    @RequestMapping(value = AuthUrl.UPDATE_ROLE_STATUS)
    @LimitTime
    public void updateRoleStatus(@RequestBody @Valid RoleStatusReqVo roleStatusReqVo,BindingResult result) throws ResultException {
        menuRoleService.updateRoleStatus(roleStatusReqVo.getId(),roleStatusReqVo.isStatus());
    }


}
