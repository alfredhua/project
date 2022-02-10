package com.site.controller.website;

import com.common.aspect.annotation.LimitTime;
import com.common.util.BeanCopyUtil;
import com.pro.website.dto.entity.Banner;
import com.site.controller.common.BaseController;
import com.site.controller.website.vo.banner.BannerListRespVo;
import com.site.controller.website.vo.banner.BannerTypeReqVo;
import com.website.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST)
@Slf4j
public class BannerController extends BaseController {

    @Autowired
    BannerService bannerService;

    @RequestMapping(value = WebsiteUrl.LIST_BANNER_BY_TYPE)
    @LimitTime
    public List<BannerListRespVo> listBannersByType(@RequestBody @Valid BannerTypeReqVo bannerTypeReqVo, BindingResult result){
            List<Banner> banners = bannerService.listBannersByType(BeanCopyUtil.copy(bannerTypeReqVo, Banner.class));
            return listReturn(banners,BannerListRespVo.class);
    }

}
