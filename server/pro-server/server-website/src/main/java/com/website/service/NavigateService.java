package com.website.service;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.mybatis.enums.ConditionEnum;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.website.dao.NavigateMapper;
import com.website.entity.Navigate;
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

    public PageBean<Navigate> listNavigateByPage(PageRequest pageRequest) {
        PageBean<Navigate> pageBean = PageUtil.getPageBean(pageRequest.getPage_num(),pageRequest.getPage_size(),pageRequest.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(navigateMapper.listByPage(pageBean.getPage_num(),pageBean.getPage_size(),entityWrapper));
        pageBean.setTotal(navigateMapper.listCount(entityWrapper));
        return pageBean;
    }

    public List<Navigate> listNavigateByType(String oneType,String twoType) {
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.addCondition("one_type", ConditionEnum.eq,oneType);
        entityWrapper.addCondition("two_type", ConditionEnum.eq,twoType);
        return navigateMapper.listAll(entityWrapper);
    }
}
