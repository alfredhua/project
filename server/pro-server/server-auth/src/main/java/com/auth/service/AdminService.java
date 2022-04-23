package com.auth.service;

import com.auth.constants.admin.AdminStatusEnum;
import com.auth.dao.AdminMapper;
import com.auth.entity.Admin;
import com.common.api.constants.SysErrorCodeEnum;
import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.api.exception.ResultException;
import com.common.mybatis.entity.EntityWrapper;
import com.common.util.IDGenerateUtil;
import com.common.util.MessageDigestUtil;
import com.common.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author hua
 */

@Component
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AuthDataAdminService authDataAdminService;

    public void createAdmin(Admin admin, List<String> authDataCode) throws Exception{
        validate(admin);
        admin.setPassword(MessageDigestUtil.resetPassword());
        admin.setId(IDGenerateUtil.generateId());
        adminMapper.insert(admin);
        authDataAdminService.save(authDataCode,admin.getId());
    }

    private void validate(Admin admin) throws ResultException{
        if(adminMapper.validateEmail(admin.getEmail(),admin.getId())>0){
            throw ResultException.error(SysErrorCodeEnum.EMAIL_ERROR);
        }
        if(adminMapper.validatePhone(admin.getPhone(),admin.getId())>0){
            throw ResultException.error(SysErrorCodeEnum.PHONE_ERROR);
        }
        if(adminMapper.validateUserName(admin.getUser_name(),admin.getId())>0){
            throw ResultException.error(SysErrorCodeEnum.USER_NAME_ERROR);
        }
    }

    public void updateAdmin(Admin admin,List<String> authDataCode) throws Exception {
        validate(admin);
        if(adminMapper.updateById(admin)){
            authDataAdminService.save(authDataCode,admin.getId());
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }


    public  Admin getAdminById(long id) throws Exception {
        Admin adminRespDTO = adminMapper.queryById(id);
        if (adminRespDTO!=null){
//            adminRespDTO.setAuth_data_code(authDataAdminService.listByAdminId(id));
            return adminRespDTO;
        }
        throw ResultException.error(SysErrorCodeEnum.GET_ERROR);
    }

    public PageBean<Admin> listAdminByPage(PageRequest pageRequest) {
        PageBean<Admin> pageBean = PageUtil.getPageBean(pageRequest.getPage_num(),pageRequest.getPage_size(),pageRequest.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(adminMapper.listByPage(pageRequest.getPage_num(),pageRequest.getPage_size(), entityWrapper));
        pageBean.setTotal(adminMapper.listCount(entityWrapper));
        return pageBean;
    }


    public void updateActiveAdmin(String id,short isActive) {
        if(isActive== AdminStatusEnum.ACTIVE.getCode() && adminMapper.updateUnFrozenAdmin(id)){
            return;
        }
        adminMapper.updateFrozenAdmin(id);
    }


    public boolean resetAdminPassword(long id) throws Exception {
      return adminMapper.resetAdminPassword(id,MessageDigestUtil.resetPassword());
    }


    public void updatePassword(long id, String old_password, String confirm_password) throws Exception {
        Admin adminRespDTO = adminMapper.queryById(id);
        if (!adminRespDTO.getPassword().equals(MessageDigestUtil.base64AndMD5(old_password))){
            throw ResultException.error(SysErrorCodeEnum.OLD_PASSWORD_ERROR);
        }
        if (adminMapper.resetAdminPassword(id,MessageDigestUtil.base64AndMD5(confirm_password))){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.UPDATE_PASSWORD_ERROR);
    }

    public void updateAdminInfo(Admin admin)throws Exception {
        if(adminMapper.validateEmail(admin.getEmail(),admin.getId())>0){
            throw ResultException.error(SysErrorCodeEnum.EMAIL_ERROR);
        }
        if(adminMapper.validatePhone(admin.getPhone(),admin.getId())>0){
            throw ResultException.error(SysErrorCodeEnum.PHONE_ERROR);
        }
        if (!adminMapper.updateById(admin)){
            throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
        }
    }

}
