package com.website.service;


import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.mybatis.enums.ConditionEnum;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.pro.api.entity.website.BannerListReqDto;
import com.website.dao.BannerMapper;
import com.website.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author hua
 */

@Component
public class BannerService {

    @Autowired
    BannerMapper bannerMapper;

    public void createBanner(Banner bannerReqDTO) {
        bannerReqDTO.setId(IDGenerateUtil.generateId());
        bannerMapper.insert(bannerReqDTO);
    }

    public boolean updateBanner(Banner bannerReqDTO){
        return bannerMapper.updateById(bannerReqDTO);
    }

    public boolean delBanner(long id){
        return bannerMapper.deleteById(id);
    }

    public PageBean<Banner> listBanners(BannerListReqDto bannerListReqDTO) {
        PageBean<Banner> pageBean = PageUtil.getPageBean(bannerListReqDTO.getPage_num(),bannerListReqDTO.getPage_size(),bannerListReqDTO.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(bannerMapper.listByPage(pageBean.getPage_num(),pageBean.getPage_size(),entityWrapper));
        pageBean.setTotal(bannerMapper.listCount(entityWrapper));
        return pageBean;
    }

    
    public Banner getBannerById(long id) {
        return bannerMapper.queryById(id);
    }


    public List<Banner> listBannersByType(String type) {
        EntityWrapper entityWrapper=new EntityWrapper();
         entityWrapper.addCondition("type", ConditionEnum.eq,type);
        return bannerMapper.listAll(entityWrapper);
    }

}
