package com.website.service;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.IDGenerate;
import com.common.util.PageUtil;
import com.website.dao.PartnerMapper;
import com.website.dto.entity.Partner;
import com.website.dto.PartnerListReqDTO;
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
        partnerReqDTO.setId(IDGenerate.generateId());
        partnerMapper.createPartner(partnerReqDTO);
    }

    
    public Partner getById(String id) {
        return partnerMapper.getById(id);
    }

    
    public void updatePartner(Partner partnerReqDTO) throws Exception {
        if(partnerMapper.updatePartner(partnerReqDTO)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }

    
    public void delPartner(String id) throws Exception {
        if(partnerMapper.delPartner(id)){
            return ;
        }
        throw ResultException.error(SysErrorCodeEnum.DEL_ERROR);
    }

    
    public PageBean<Partner> listPartnerByPage(PartnerListReqDTO partnerListReqDTO) {
        PageBean<Partner> pageBean = PageUtil.validatePage(partnerListReqDTO.getPage_num(),
                partnerListReqDTO.getPage_size(),partnerListReqDTO.getOffset());
        pageBean.setList(partnerMapper.listPartnerByPage(partnerListReqDTO.getOffset(),partnerListReqDTO.getPage_size()));
        pageBean.setTotal(partnerMapper.listPartnerCount());
        return pageBean;
    }

    
    public List<List<Partner>> listAllPartner() {
        List<List<Partner>> list = new ArrayList<>();
        int count = partnerMapper.listPartnerCount();
        int page_size=6;
        int page= count/page_size+1;
        for (int i = 0; i <page; i++) {
            list.add(partnerMapper.listPartnerByPage(i*page_size,page_size));
        }
        return list;
    }
}
