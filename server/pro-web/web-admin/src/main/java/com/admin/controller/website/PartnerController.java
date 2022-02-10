package com.admin.controller.website;

import com.admin.controller.common.AdminBaseController;
import com.admin.controller.website.vo.partner.PartnerCreateReqVO;
import com.admin.controller.website.vo.partner.PartnerListReqVO;
import com.admin.controller.website.vo.partner.PartnerRespVO;
import com.admin.controller.website.vo.partner.PartnerUpdateReqVO;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.website.dto.PartnerListReqDTO;
import com.pro.website.dto.entity.Partner;
import com.website.service.PartnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Api(tags = "合作伙伴管理")
@RestController
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class PartnerController  extends AdminBaseController {


    @Autowired
    PartnerService partnerService;

    @ApiOperation(value  ="合作伙伴创建")
    @RequestMapping(value = WebsiteUrl.CREATE_PARTNER)
    public void createPartner(@RequestBody @Valid PartnerCreateReqVO partnerCreateReqVO, BindingResult result){
        Partner partnerReqDTO = BeanCopyUtil.copy(partnerCreateReqVO, Partner.class);
        partnerService.createPartner(partnerReqDTO);
    }

    @ApiOperation(value  ="合作伙伴更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_PARTNER)
    public void updatePartner(@RequestBody @Valid PartnerUpdateReqVO partnerUpdateReqVO, BindingResult result) throws Exception {
        Partner partnerReqDTO = BeanCopyUtil.copy(partnerUpdateReqVO, Partner.class);
        partnerService.updatePartner(partnerReqDTO);
    }

    @ApiOperation(value  ="合作伙伴获取")
    @RequestMapping(value = WebsiteUrl.GET_PARTNER)
    public PartnerRespVO getById(@PathVariable("id") String id){
        return resultReturn(partnerService.getById(id),PartnerRespVO.class);
    }

    @ApiOperation(value  ="合作伙伴删除")
    @RequestMapping(value = WebsiteUrl.DEL_PARTNER)
    public void delPartner(@PathVariable("id") String id) throws Exception {
         partnerService.delPartner(id);
    }

    @ApiOperation(value  ="合作伙伴列表")
    @RequestMapping(value = WebsiteUrl.LIST_PARTNER)
    public PageBean<PartnerRespVO> listPartnerByPage(@RequestBody @Valid PartnerListReqVO partnerListReqVO, BindingResult result){
        PartnerListReqDTO partnerListReqDTO = BeanCopyUtil.copy(partnerListReqVO, PartnerListReqDTO.class);
        PageBean<Partner>  pageBean = partnerService.listPartnerByPage(partnerListReqDTO);
        return pageResultReturn(pageBean,PartnerRespVO.class);
    }

}
