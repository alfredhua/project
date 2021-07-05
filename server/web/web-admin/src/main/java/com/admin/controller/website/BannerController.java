package com.admin.controller.website;

import com.admin.controller.common.AdminBaseController;
import com.admin.controller.website.vo.banner.*;
import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.website.dto.entity.Banner;
import com.website.dto.BannerListReqDTO;
import com.website.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author  guozhenhua
 * @  2018/11/10
 */
@Api(tags="banner管理")
@RestController
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class BannerController extends AdminBaseController {


    @Autowired
    BannerService bannerService;

    @ApiOperation(value = "banner创建")
    @RequestMapping(value = WebsiteUrl.CREATE_BANNER)
    public void createBanner(@RequestBody @Valid BannerCreateReqVo bannerCreateReqVo, BindingResult result){
        Banner bannerReqDTO = BeanCopyUtil.copy(bannerCreateReqVo, Banner.class);
        bannerService.createBanner(bannerReqDTO);
    }

    @ApiOperation(value = "banner编辑")
    @RequestMapping(value = WebsiteUrl.UPDATE_BANNER)
    public void updateBanner(@RequestBody @Valid BannerUpdateReqVo bannerUpdateReqVo, BindingResult result)  throws Exception {
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
        if (StringUtils.isEmpty(id)){
            throw ResultException.error(SysErrorCodeEnum.ERR_ILLEGAL_PARAM);
        }
        return resultReturn(bannerService.getBannerById(id),BannerRespVo.class);
    }

    @ApiOperation(value = "banner列表")
    @RequestMapping(value = WebsiteUrl.LIST_BANNER)
    public PageBean<BannerListRespVo> listBanners(@RequestBody @Valid BannerListReqVo bannerListReqVo, BindingResult result){
        BannerListReqDTO bannerListReqDTO = BeanCopyUtil.copy(bannerListReqVo, BannerListReqDTO.class);
        PageBean<Banner> pageBean= bannerService.listBanners(bannerListReqDTO);
        return pageResultReturn(pageBean,BannerListRespVo.class);
    }


}
