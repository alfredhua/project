package com.website.service;


import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.IDGenerate;
import com.common.util.PageUtil;
import com.website.dao.BannerMapper;
import com.pro.website.dto.entity.Banner;
import com.pro.website.dto.BannerListReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hua
 */

@Component
public class BannerService {

    @Autowired
    BannerMapper bannerMapper;

    public void createBanner(Banner bannerReqDTO) {
        bannerReqDTO.setId(IDGenerate.generateId());
        bannerMapper.createBanner(bannerReqDTO);
    }

    public void updateBanner(Banner bannerReqDTO) throws Exception {
        if(!bannerMapper.updateBanner(bannerReqDTO)){
            throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
        }
    }

    public void delBanner(long id) throws Exception {
        if(!bannerMapper.delBanner(id)){
            throw ResultException.error(SysErrorCodeEnum.DEL_ERROR);
        }
    }

    public PageBean<Banner> listBanners(BannerListReqDTO bannerListReqDTO) {
        PageBean<Banner> pageBean = PageUtil.validatePage(bannerListReqDTO.getPage_num(),
                bannerListReqDTO.getPage_size(),bannerListReqDTO.getOffset());
        pageBean.setList(bannerMapper.listBanners(pageBean.getOffset(),pageBean.getPage_size()));
        pageBean.setTotal(bannerMapper.listBannerCount());
        return pageBean;
    }

    
    public Banner getBannerById(long id) {
        return bannerMapper.getBannerById(id);
    }


    public List<Banner> listBannersByType(Banner bannerReqDTO) {
        return bannerMapper.listBannersByType(bannerReqDTO.getType());
    }

}
