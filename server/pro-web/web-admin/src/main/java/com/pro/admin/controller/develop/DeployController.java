package com.pro.admin.controller.develop;

import com.common.api.entity.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.common.util.LoginUtil;
import com.develop.entity.Deploy;
import com.develop.service.DeployService;
import com.pro.admin.controller.develop.vo.DevelopUpdateReqVO;
import com.pro.api.entity.auth.DeployListReqDto;
import com.pro.admin.controller.develop.vo.DeployListReqVo;
import com.pro.admin.controller.develop.vo.DeployListRespVo;
import com.pro.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author guozhenhua
 * @date 2021/01/17
 */
@Api(tags="配置中心")
@RestController
@RequestMapping(value = DevelopUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class DeployController extends BaseController {

    @Autowired
    DeployService deployService;

    @ApiOperation(value = "配置更新")
    @RequestMapping(value = DevelopUrl.UPDATE_DEPLOY)
    public void updateDevelop(@RequestBody @Valid DevelopUpdateReqVO developUpdateReqVO ){
        Deploy deployReqDTO = BeanCopyUtil.copy(developUpdateReqVO, Deploy.class);
        deployReqDTO.setOperator(LoginUtil.getLoginUser().getUser_name());
        deployService.update(deployReqDTO);
    }

    @ApiOperation(value = "配置删除")
    @RequestMapping(value = DevelopUrl.DELETE_DEPLOY)
    public void delDevelop(@PathVariable("name") String name) throws Exception {
        deployService.delDevelop(name,LoginUtil.getLoginUser().getUser_name());
    }

    @ApiOperation(value = "配置列表")
    @RequestMapping(value = DevelopUrl.LIST_DEPLOY)
    public PageBean<DeployListRespVo> listDeploy(@RequestBody @Valid DeployListReqVo deployListReqVo ){
        return pageResultReturn(
                deployService.listDeploy(BeanCopyUtil.copy(deployListReqVo, DeployListReqDto.class)),
                DeployListRespVo.class
        );
    }

}
