package com.pro.site.controller;

import com.common.aspect.annotation.LimitTime;
import com.common.util.BeanCopyUtil;
import com.pro.common.controller.BaseController;
import com.pro.site.controller.vo.partner.PartnerResponseVO;
import com.website.entity.Partner;
import com.website.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guozhenhua
 * @date 2019/07/20
 */
@RestController
@RequestMapping(value = SiteWebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class SitePartnerController extends BaseController {

    @Autowired
    PartnerService partnerService;

    @RequestMapping(value = SiteWebsiteUrl.LIST_ALL_PARTNER)
    @LimitTime
    public List<List<PartnerResponseVO>> listAllPartner(){
        List<List<Partner>> listPartner = partnerService.listAllPartner();
        List<List<PartnerResponseVO>> list = new ArrayList<>();
        for (List<Partner> list1:listPartner) {
            list.add(BeanCopyUtil.copyList(list1, PartnerResponseVO.class));
        }
        return list;
    }

}
