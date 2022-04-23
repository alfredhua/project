package com.auth.service;

import com.auth.dao.MenuRoleMapper;
import com.auth.entity.MenuRole;
import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.util.IDGenerateUtil;
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
        menuRole.setId(IDGenerateUtil.generateId());
        menuRoleMapper.insert(menuRole);
    }

    public void updateRole(MenuRole menuRoleReqDTO){
        menuRoleMapper.updateById(menuRoleReqDTO);
    }

    public PageBean<MenuRole> listRole(PageRequest pageRequest) {
        PageBean<MenuRole> pageBean = PageUtil.getPageBean(pageRequest.getPage_num(),pageRequest.getPage_size(), pageRequest.getOffset());
        pageBean.setList(menuRoleMapper.listByPage(pageBean.getPage_num(),pageBean.getPage_size(),null));
        pageBean.setTotal(menuRoleMapper.listCount(null));
        return pageBean;
    }

    public  List<MenuRole>  listAllUseRole() {
        EntityWrapper entityWrapper=new EntityWrapper();
        return menuRoleMapper.listAll(entityWrapper);
    }

    public MenuRole getRoleById(long id) {
        return menuRoleMapper.queryById(id);
    }

    public void updateRoleStatus(long id, boolean status){
        MenuRole menuRole=new MenuRole();
        menuRole.setId(id);
        menuRole.setStatus(status);
        menuRoleMapper.updateById(menuRole);
    }

}
