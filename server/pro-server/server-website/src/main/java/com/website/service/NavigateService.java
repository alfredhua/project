package com.website.service;

import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.pro.website.dto.NavigateListReqDTO;
import com.pro.website.dto.entity.Navigate;
import com.website.dao.NavigateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @auth guozhenhua
 * @date ${ctime}
 */
@Component
public class NavigateService {

    @Autowired
    NavigateMapper navigateMapper;

    public void createNavigate(Navigate navigate) {
        navigate.setId(IDGenerateUtil.generateId());
        navigate.setCreate_time(LocalDateTime.now());
        navigateMapper.insert(navigate);
    }

     public Navigate getById(long id) {
         return navigateMapper.queryById(id);
     }

    public boolean updateNavigate(Navigate navigate) {
        return navigateMapper.updateById(navigate);

    }

    public boolean delNavigate(long id) {
        return navigateMapper.deleteById(id);
    }

    public PageBean<Navigate> listNavigateByPage(NavigateListReqDTO navigateListReqDTO) {
        PageBean<Navigate> pageBean = PageUtil.getPageBean(navigateListReqDTO.getPage_num(),navigateListReqDTO.getPage_size(),navigateListReqDTO.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(navigateMapper.listByPage(pageBean.getPage_num(),pageBean.getPage_size(),entityWrapper));
        pageBean.setTotal(navigateMapper.listCount(entityWrapper));
        return pageBean;
    }

    public List<Navigate> listNavigateByType(NavigateListReqDTO navigateListReqDTO) {
        EntityWrapper entityWrapper=new EntityWrapper();
        return navigateMapper.listAll(entityWrapper);
    }
}
