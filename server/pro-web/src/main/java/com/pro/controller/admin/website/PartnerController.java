package com.pro.controller.admin.website;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.controller.admin.website.vo.partner.PartnerCreateReqVO;
import com.pro.controller.admin.website.vo.partner.PartnerListReqVO;
import com.pro.controller.admin.website.vo.partner.PartnerRespVO;
import com.pro.controller.admin.website.vo.partner.PartnerUpdateReqVO;
import com.pro.controller.common.BaseController;
import com.website.entity.Partner;
import com.website.service.PartnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Api(tags = "合作伙伴管理")
@RestController
@RequestMapping(value = WebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class PartnerController  extends BaseController {


    @Autowired
    PartnerService partnerService;

    @ApiOperation(value  ="合作伙伴创建")
    @RequestMapping(value = WebsiteUrl.CREATE_PARTNER)
    public void createPartner(@RequestBody @Valid PartnerCreateReqVO partnerCreateReqVO ){
        partnerService.createPartner(BeanCopyUtil.copy(partnerCreateReqVO, Partner.class));
    }

    @ApiOperation(value  ="合作伙伴更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_PARTNER)
    public void updatePartner(@RequestBody @Valid PartnerUpdateReqVO partnerUpdateReqVO ) throws Exception {
        Partner partnerReqDTO = BeanCopyUtil.copy(partnerUpdateReqVO, Partner.class);
        partnerService.updatePartner(partnerReqDTO);
    }

    @ApiOperation(value  ="合作伙伴获取")
    @RequestMapping(value = WebsiteUrl.GET_PARTNER)
    public PartnerRespVO getById(@PathVariable("id") long id){
        return resultReturn(partnerService.getById(id),PartnerRespVO.class);
    }

    @ApiOperation(value  ="合作伙伴删除")
    @RequestMapping(value = WebsiteUrl.DEL_PARTNER)
    public void delPartner(@PathVariable("id") long id) throws Exception {
         partnerService.delPartner(id);
    }

    @ApiOperation(value  ="合作伙伴列表")
    @RequestMapping(value = WebsiteUrl.LIST_PARTNER)
    public PageBean<PartnerRespVO> listPartnerByPage(@RequestBody @Valid PartnerListReqVO partnerListReqVO ){
        return pageResultReturn(
                partnerService.listPartnerByPage(new PageRequest(partnerListReqVO.getPage_num(),partnerListReqVO.getPage_size())),
                PartnerRespVO.class);
    }

}
