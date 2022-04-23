package com.website.service;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.website.dao.ProduceMapper;
import com.website.entity.Produce;
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
        produceReqDTO.setId(IDGenerateUtil.generateId());
        produceMapper.insert(produceReqDTO);
    }


    public Produce getById(Long id) {
        return produceMapper.queryById(id);
    }


    public boolean updateProduce(Produce produceReqDTO) throws Exception {
        return produceMapper.updateById(produceReqDTO);
    }


    public boolean delProduce(Long id) throws Exception {
        return produceMapper.deleteById(id);
    }



    public PageBean<Produce> listProduceByPage(PageRequest pageRequest) {
        PageBean<Produce> pageBean = PageUtil.getPageBean(pageRequest.getPage_num(),pageRequest.getPage_size(),pageRequest.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(produceMapper.listByPage(pageBean.getPage_num(),pageBean.getPage_size(),entityWrapper));
        pageBean.setTotal(produceMapper.listCount(entityWrapper));
        return pageBean;
    }


    public List<Produce> listProduceHome() {
        EntityWrapper entityWrapper=new EntityWrapper();
        return produceMapper.listAll(entityWrapper);
    }


    public List<Produce> listAllProduce() {
        EntityWrapper entityWrapper=new EntityWrapper();
        return produceMapper.listAll(entityWrapper);
    }


}
