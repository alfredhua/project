package com.site.controller.website;

import com.common.util.BeanCopyUtil;
import com.pro.website.dto.NavigateListReqDTO;
import com.pro.website.dto.entity.Navigate;
import com.site.controller.common.BaseController;
import com.site.controller.website.vo.Navigate.NavigateListReqVO;
import com.site.controller.website.vo.Navigate.NavigateRespVO;
import com.website.service.NavigateService;
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
 * @auth guozhenhua
 * @date 2021/08/23
 */
@RestController
@RequestMapping(value = "/site/navigate",method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class NavigateController extends BaseController {

    @Autowired
    NavigateService navigateService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public List<NavigateRespVO> listNavigateByType(@RequestBody @Valid NavigateListReqVO navigateListReq, BindingResult result){
        NavigateListReqDTO navigateListReqDTO = BeanCopyUtil.copy(navigateListReq, NavigateListReqDTO.class);
        List<Navigate> list= navigateService.listNavigateByType(navigateListReqDTO);
        return listReturn(list,NavigateRespVO.class);
    }

}
