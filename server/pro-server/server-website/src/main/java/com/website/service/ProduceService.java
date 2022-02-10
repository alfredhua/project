package com.website.service;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.IDGenerate;
import com.common.util.PageUtil;
import com.website.dao.ProduceMapper;
import com.pro.website.dto.ProduceListReqDTO;
import com.pro.website.dto.entity.Produce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Component
public class ProduceService{

    @Autowired
    ProduceMapper produceMapper;


    public void createProduce(Produce produceReqDTO) {
        produceReqDTO.setId(IDGenerate.generateId());
        produceMapper.createProduce(produceReqDTO);
    }

    
    public Produce getById(String id) {
        return produceMapper.getById(id);
    }

    
    public void updateProduce(Produce produceReqDTO) throws Exception {
        if(produceMapper.updateProduce(produceReqDTO)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }


    public void delProduce(String id) throws Exception {
        if(produceMapper.delProduce(id)){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }


    
    public PageBean<Produce> listProduceByPage(ProduceListReqDTO produceListReqDTO) {
        PageBean<Produce> pageBean = PageUtil.validatePage(produceListReqDTO.getPage_num(),
                produceListReqDTO.getPage_size(),produceListReqDTO.getOffset());
        pageBean.setList(produceMapper.listProduceByPage(produceListReqDTO));
        pageBean.setTotal(produceMapper.listProduceCount(produceListReqDTO));
        return pageBean;
    }

    
    public List<Produce> listProduceHome() {
       return produceMapper.listProduceHome();
    }


    public List<Produce> listAllProduce() {
        return produceMapper.listProduceHome();
    }


}
