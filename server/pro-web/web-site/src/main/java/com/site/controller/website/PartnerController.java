package com.site.controller.website;

import com.common.aspect.annotation.LimitTime;
import com.common.util.BeanCopyUtil;
import com.pro.website.dto.entity.Partner;
import com.site.controller.common.BaseController;
import com.site.controller.website.vo.partner.PartnerResponseVO;
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
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class PartnerController extends BaseController {

    @Autowired
    PartnerService partnerService;

    @RequestMapping(value = WebsiteUrl.LIST_ALL_PARTNER)
    @LimitTime
    public List<List<PartnerResponseVO>> listAllPartner(){
        List<List<Partner>> listPartner = partnerService.listAllPartner();
        List<List<PartnerResponseVO>> list = new ArrayList<>();
        for (List<Partner> list1:listPartner) {
            list.add( BeanCopyUtil.copyList(list1, PartnerResponseVO.class));
        }
        return list;
    }

}
