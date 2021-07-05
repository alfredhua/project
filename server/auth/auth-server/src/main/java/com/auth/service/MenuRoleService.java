package com.auth.service;

import com.auth.dao.MenuRoleMapper;
import com.auth.dto.entity.MenuRole;
import com.auth.dto.MenuRoleListReqDTO;
import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.IDGenerate;
import com.common.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hua
 */
@Component
public class MenuRoleService {

    @Autowired
    MenuRoleMapper menuRoleMapper;

    public void createRole(MenuRole menuRole) {
        menuRole.setId(IDGenerate.generateId());
        menuRoleMapper.createRole(menuRole);
    }

    public void updateRole(MenuRole menuRoleReqDTO) throws Exception{
        if(menuRoleMapper.updateRole(menuRoleReqDTO)){
           return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }

    public PageBean<MenuRole> listRole(MenuRoleListReqDTO menuRoleListReqDTO) {
        PageBean<MenuRole> pageBean = PageUtil.validatePage(menuRoleListReqDTO.getPage_num(),
                menuRoleListReqDTO.getPage_size(), menuRoleListReqDTO.getOffset());
        pageBean.setList(menuRoleMapper.listRole(pageBean.getOffset(),pageBean.getPage_size()));
        pageBean.setTotal(menuRoleMapper.listRoleCount());
        return pageBean;
    }

    public  List<MenuRole>  listAllUseRole() {
        return menuRoleMapper.listAllUseRole();
    }

    public MenuRole getRoleById(long id) {
        return menuRoleMapper.getRoleById(id);
    }

    public void updateRoleStatus(long id, boolean status) throws ResultException {
        //禁用
        if(status && menuRoleMapper.updateActiveStatus(id)){
            return;
        }
        if ( menuRoleMapper.updateUnActiveStatus(id)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }

}
