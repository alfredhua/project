package com.pro.site.controller;

import com.common.aspect.annotation.LimitTime;
import com.pro.controller.common.BaseController;
import com.pro.site.controller.vo.banner.BannerListRespVo;
import com.pro.site.controller.vo.banner.BannerTypeReqVo;
import com.website.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2018/11/14
 */
@RestController
@RequestMapping(value = SiteWebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST)
@Slf4j
public class SiteBannerController extends BaseController {

    @Autowired
    BannerService bannerService;

    @RequestMapping(value = SiteWebsiteUrl.LIST_BANNER_BY_TYPE)
    @LimitTime
    public List<BannerListRespVo> listBannersByType(@RequestBody @Valid BannerTypeReqVo bannerTypeReqVo){
        return listReturn( bannerService.listBannersByType(bannerTypeReqVo.getType()), BannerListRespVo.class);
    }

}
