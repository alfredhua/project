package com.pro.admin.controller.website;

import com.common.api.constants.SysErrorCodeEnum;
import com.common.api.entity.response.PageBean;
import com.common.api.exception.ResultException;
import com.common.util.BeanCopyUtil;
import com.pro.admin.controller.website.vo.banner.*;
import com.pro.api.entity.website.BannerListReqDto;
import com.pro.controller.admin.website.vo.banner.*;
import com.pro.controller.common.BaseController;
import com.website.entity.Banner;
import com.website.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author  guozhenhua
 * @  2018/11/10
 */
@Api(tags="banner管理")
@RestController
@RequestMapping(value = WebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class BannerController extends BaseController {

    @Autowired
    BannerService bannerService;

    @ApiOperation(value = "banner创建")
    @RequestMapping(value = WebsiteUrl.CREATE_BANNER)
    public void createBanner(@RequestBody @Validated BannerCreateReqVo bannerCreateReqVo){
        Banner bannerReqDTO = BeanCopyUtil.copy(bannerCreateReqVo, Banner.class);
        bannerService.createBanner(bannerReqDTO);
    }

    @ApiOperation(value = "banner编辑")
    @RequestMapping(value = WebsiteUrl.UPDATE_BANNER)
    public void updateBanner(@RequestBody @Valid BannerUpdateReqVo bannerUpdateReqVo )  throws Exception {
        Banner bannerReqDTO = BeanCopyUtil.copy(bannerUpdateReqVo, Banner.class);
        bannerService.updateBanner(bannerReqDTO);
    }

    @ApiOperation(value = "banner删除")
    @RequestMapping(value = WebsiteUrl.DELETE_BANNER)
    public void delBanner(@PathVariable("id") long id)throws Exception {
         bannerService.delBanner(id);
    }

    @ApiOperation(value = "获取banner信息")
    @RequestMapping(value = WebsiteUrl.GET_BANNER)
    public BannerRespVo getBannerById(long id) throws Exception {
        if (ObjectUtils.isEmpty(id)){
            throw ResultException.error(SysErrorCodeEnum.ERR_ILLEGAL_PARAM);
        }
        return resultReturn(bannerService.getBannerById(id),BannerRespVo.class);
    }

    @ApiOperation(value = "banner列表")
    @RequestMapping(value = WebsiteUrl.LIST_BANNER)
    public PageBean<BannerListRespVo> listBanners(@RequestBody @Valid BannerListReqVo bannerListReqVo ){
        BannerListReqDto bannerListReqDTO = BeanCopyUtil.copy(bannerListReqVo, BannerListReqDto.class);
        PageBean<Banner> pageBean= bannerService.listBanners(bannerListReqDTO);
        return pageResultReturn(pageBean,BannerListRespVo.class);
    }


}
