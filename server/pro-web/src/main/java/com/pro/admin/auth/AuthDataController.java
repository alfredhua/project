package com.pro.admin.auth;

import com.pro.admin.common.AdminBaseController;
import com.pro.admin.auth.vo.admin.AuthDataListReqVo;
import com.pro.admin.auth.vo.admin.AuthDataReqVo;
import com.pro.admin.auth.vo.admin.AuthDataRespVo;
import com.auth.service.AuthDataService;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.auth.dto.AuthDataReqDTO;
import com.pro.auth.dto.entity.AuthData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author guozhenhua
 * @date 2021/05/23
 */
@Api(tags = "数据权限管理")
@RestController
@RequestMapping(value = AuthUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class AuthDataController extends AdminBaseController {

    @Autowired
    AuthDataService dataService;

    @ApiOperation(value="数据权限列表")
    @RequestMapping(value=AuthUrl.LIST_AUTH_DATA)
    public PageBean<AuthDataRespVo> listAuthData(@RequestBody @Valid AuthDataListReqVo adminListReqVO){
        AuthDataReqDTO authDataReqDTO = BeanCopyUtil.copy(adminListReqVO, AuthDataReqDTO.class);
        return pageResultReturn( dataService.listAuthDataByPage(authDataReqDTO), AuthDataRespVo.class);
    }

    @ApiOperation(value ="数据权限创建")
    @RequestMapping(value=AuthUrl.SAVE_AUTH_DATA)
    public void saveAuthData(@RequestBody @Valid AuthDataReqVo authDataReqVo,BindingResult result) throws Exception {

        AuthData authData = BeanCopyUtil.copy(authDataReqVo, AuthData.class);
        dataService.saveAuthData(authData);
    }

    @ApiOperation(value ="数据权限获取")
    @RequestMapping(value=AuthUrl.LIST_AUTH_DATA_ALL)
    public List<AuthDataRespVo> listAuthDataAll(){
        return listReturn(dataService.listAuthDataAll(),AuthDataRespVo.class);
    }

}
