package com.website.service;

import com.pro.website.dto.NavigateListReqDTO;
import com.pro.website.dto.entity.Navigate;
import com.website.dao.NavigateMapper;
import com.common.domain.response.PageBean;
import com.common.util.IDGenerate;
import com.common.util.PageUtil;
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
        navigate.setId(IDGenerate.generateId());
        navigate.setCreate_time(LocalDateTime.now());
        navigateMapper.createNavigate(navigate);
    }

     public Navigate getById(long id) {
         return navigateMapper.getById(id);
     }

    public boolean updateNavigate(Navigate navigate) {
        return navigateMapper.updateNavigate(navigate);

    }

    public boolean delNavigate(long id) {
        return navigateMapper.delNavigate(id);
    }

    public PageBean<Navigate> listNavigateByPage(NavigateListReqDTO navigateListReqDTO) {
        PageBean<Navigate> pageBean = PageUtil.validatePage(navigateListReqDTO.getPage_num(),
                navigateListReqDTO.getPage_size(),navigateListReqDTO.getOffset());
        pageBean.setList(navigateMapper.listNavigateByPage(navigateListReqDTO));
        pageBean.setTotal(navigateMapper.listNavigateCount(navigateListReqDTO));
        return pageBean;
    }

    public List<Navigate> listNavigateByType(NavigateListReqDTO navigateListReqDTO) {
        return navigateMapper.listNavigateByType(navigateListReqDTO);
    }
}
