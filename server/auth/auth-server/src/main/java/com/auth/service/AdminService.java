package com.auth.service;

import com.auth.constants.admin.AdminStatusEnum;
import com.auth.dao.AdminMapper;
import com.auth.dto.AdminListReqDTO;
import com.auth.dto.entity.Admin;
import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.IDGenerateUtil;
import com.common.util.MessageDigestUtil;
import com.common.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author hua
 */

@Component
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AuthDataAdminService authDataAdminService;

    private void validate(Admin admin)throws ResultException{
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

    public void createAdmin(Admin admin)throws Exception{
        validate(admin);
        admin.setPassword(MessageDigestUtil.resetPassword());
        admin.setId(IDGenerateUtil.generateId());
        adminMapper.insert(admin);
        authDataAdminService.save(admin.getAuth_code_list(),admin.getId());
    }


    public void updateAdmin(Admin admin)throws Exception {
        validate(admin);
        if(adminMapper.updateAdmin(admin)){
            authDataAdminService.save(admin.getAuth_code_list(),admin.getId());
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }


    public  Admin getAdminById(long id)throws Exception {
        Admin adminRespDTO = adminMapper.getAdminById(id);
        if (adminRespDTO!=null){
            adminRespDTO.setAuth_code_list(authDataAdminService.listByAdminId(id));
            return adminRespDTO;
        }
        throw ResultException.error(SysErrorCodeEnum.GET_ERROR);
    }

    public PageBean<Admin> listAdminByPage(AdminListReqDTO adminListReqDTO) {
        PageBean<Admin> pageBean = PageUtil.validatePage(adminListReqDTO.getPage_num(),
                adminListReqDTO.getPage_size(),adminListReqDTO.getOffset());
        pageBean.setList(adminMapper.listAdminByPage(adminListReqDTO));
        pageBean.setTotal(adminMapper.listAdminCount(adminListReqDTO));
        return pageBean;
    }


    public void updateActiveAdmin(String id,short isActive) throws ResultException {
        if(isActive== AdminStatusEnum.ACTIVE.getCode() && adminMapper.updateUnFrozenAdmin(id)){
            return;
        }
        if (adminMapper.updateFrozenAdmin(id)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);

    }


    public void resetAdminPassword(long id) throws Exception {
       if (!adminMapper.resetAdminPassword(id,MessageDigestUtil.resetPassword())){
           throw ResultException.error(SysErrorCodeEnum.EMAIL_ERROR);
       }
    }


    public void updatePassword(long id, String old_password, String confirm_password) throws Exception {
        Admin adminRespDTO = adminMapper.getAdminById(id);
        if (!adminRespDTO.getPassword().equals(MessageDigestUtil.base64AndMD5(old_password))){
            throw ResultException.error(SysErrorCodeEnum.OLD_PASSWORD_ERROR);
        }
        if (adminMapper.resetAdminPassword(id,MessageDigestUtil.base64AndMD5(confirm_password))){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.UPDATE_PASSWORD_ERROR);
    }


    public void updateAdminInfo(long id, String phone, String email)throws Exception {
        if(adminMapper.validateEmail(email,id)>0){
           throw ResultException.error(SysErrorCodeEnum.EMAIL_ERROR);
        }
        if(adminMapper.validatePhone(phone,id)>0){
            throw ResultException.error(SysErrorCodeEnum.PHONE_ERROR);
        }
        if (!adminMapper.updateAdminInfo(id,phone,email)){
            throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
        }
    }

    public void setAuthData(long id, String auth_data_code)throws Exception {
        if(!adminMapper.setAuthData(id,auth_data_code)){
            throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
        }
    }
}
