package com.website.service;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.website.dao.PartnerMapper;
import com.website.entity.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Component
public class PartnerService {

    @Autowired
    PartnerMapper partnerMapper;

    
    public void createPartner(Partner partnerReqDTO) {
        partnerReqDTO.setId(IDGenerateUtil.generateId());
        partnerMapper.insert(partnerReqDTO);
    }

    
    public Partner getById(Long id) {
        return partnerMapper.queryById(id);
    }

    
    public boolean updatePartner(Partner partnerReqDTO){
       return partnerMapper.updateById(partnerReqDTO);
    }

    public void delPartner(Long id) throws Exception {
        partnerMapper.deleteById(id);
    }

    
    public PageBean<Partner> listPartnerByPage(PageRequest pageRequest) {
        PageBean<Partner> pageBean = PageUtil.getPageBean(pageRequest.getPage_num(),pageRequest.getPage_size(),pageRequest.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(partnerMapper.listByPage(pageRequest.getPage_num(),pageRequest.getPage_size(),entityWrapper));
        pageBean.setTotal(partnerMapper.listCount(entityWrapper));
        return pageBean;
    }

    
    public List<List<Partner>> listAllPartner() {
        List<List<Partner>> list = new ArrayList<>();
        int count = partnerMapper.listCount(null);
        int page_size=6;
        int page= count/page_size+1;
        for (int i = 0; i <page; i++) {
            list.add(partnerMapper.listByPage(page,page_size,null));
        }
        return list;
    }
}
