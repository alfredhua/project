package com.admin.controller.develop;

import com.auth.dto.LoginAdminRespDTO;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.develop.dto.entity.Deploy;
import com.develop.dto.DeployListReqDTO;
import com.develop.service.DeployService;
import com.admin.controller.common.AdminBaseController;
import com.admin.controller.develop.vo.DeployListReqVo;
import com.admin.controller.develop.vo.DeployListRespVo;
import com.admin.controller.develop.vo.DevelopUpdateReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author guozhenhua
 * @date 2021/01/17
 */
@Api(tags="配置中心")
@RestController
@RequestMapping(value = DevelopUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class DeployController extends AdminBaseController {

    @Autowired
    DeployService deployService;

    @ApiOperation(value = "配置更新")
    @RequestMapping(value = DevelopUrl.UPDATE_DEPLOY)
    public void updateDevelop(@RequestBody @Valid DevelopUpdateReqVO developUpdateReqVO,  BindingResult result) throws Exception {
        Deploy deployReqDTO = BeanCopyUtil.copy(developUpdateReqVO, Deploy.class);
        LoginAdminRespDTO admin = getLoginAdminInfo();
        deployReqDTO.setOperator(admin.getUser_name());
         deployService.update(deployReqDTO);
    }

    @ApiOperation(value = "配置删除")
    @RequestMapping(value = DevelopUrl.DELETE_DEPLOY)
    public void delDevelop(@PathVariable("name") String name) throws Exception {
         deployService.delDevelop(name,getLoginAdminInfo().getUser_name());
    }

    @ApiOperation(value = "配置列表")
    @RequestMapping(value = DevelopUrl.LIST_DEPLOY)
    public PageBean<DeployListRespVo>listDeploy(@RequestBody @Valid DeployListReqVo deployListReqVo, BindingResult result){
        DeployListReqDTO deployListReqDTO = BeanCopyUtil.copy(deployListReqVo, DeployListReqDTO.class);
        return pageResultReturn(deployService.listDeploy(deployListReqDTO), DeployListRespVo.class);
    }

}
