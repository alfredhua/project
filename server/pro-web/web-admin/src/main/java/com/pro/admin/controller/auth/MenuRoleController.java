package com.pro.admin.controller.auth;

import com.auth.entity.MenuRole;
import com.auth.service.MenuRoleService;
import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.admin.controller.auth.vo.role.*;
import com.pro.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
public class MenuRoleController extends BaseController {


    @Autowired
    MenuRoleService menuRoleService;

    @ApiOperation(value="角色保存")
    @RequestMapping(value = AuthUrl.CREATE_ROLE)
    public void createRole(@RequestBody @Valid RoleReqVO roleReqVO ){
        menuRoleService.createRole(BeanCopyUtil.copy(roleReqVO, MenuRole.class));
    }

    @ApiOperation(value="角色列表")
    @RequestMapping(value = AuthUrl.LIST_ROLE)
    public PageBean<RoleRespVo> listRole(@RequestBody @Valid RoleListReqVo roleListReqVo ){
        PageRequest pageRequest=new PageRequest();
        pageRequest.setPage_num(roleListReqVo.getPage_num());
        pageRequest.setPage_size(roleListReqVo.getPage_size());
        return pageResultReturn(
                menuRoleService.listRole(pageRequest),
                RoleRespVo.class);
    }

    @ApiOperation(value="根据id更新角色信息")
    @RequestMapping(value=AuthUrl.UPDATE_ROLE)
    public void updateRole(@RequestBody @Valid RoleUpdateReqVO roleUpdateReqVO ) throws Exception {
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
    public void updateRoleStatus(@RequestBody @Valid RoleStatusReqVo roleStatusReqVo ){
        menuRoleService.updateRoleStatus(roleStatusReqVo.getId(),roleStatusReqVo.isStatus());
    }


}
