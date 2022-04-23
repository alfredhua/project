package com.website.service;

import com.common.api.entity.response.PageBean;
import com.common.api.exception.ResultException;
import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.mybatis.enums.ConditionEnum;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.website.constant.NoticeTypeActiveEnum;
import com.website.constant.NoticeTypeErrorEnum;
import com.pro.website.dto.entity.NoticeType;
import com.website.dao.NoticeTypeMapper;
import com.pro.website.dto.NoticeTypeListReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2018/12/20
 */
@Component
public class NoticeTypeService {


    @Autowired
    NoticeTypeMapper noticeTypeMapper;

    @Autowired
    NoticeService noticeService;

    public PageBean<NoticeType> listNoticeTypeByPage(NoticeTypeListReqDTO noticeTypeListReqDTO) {
        PageBean<NoticeType> pageBean = PageUtil.getPageBean(noticeTypeListReqDTO.getPage_num(),noticeTypeListReqDTO.getPage_size(), noticeTypeListReqDTO.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(noticeTypeMapper.listByPage(pageBean.getOffset(), pageBean.getPage_size(),entityWrapper));
        pageBean.setTotal(noticeTypeMapper.listCount(entityWrapper));
        return pageBean;
    }


    public void createNoticeType(NoticeType noticeTypeReqDTO) throws Exception {
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.addCondition("type", ConditionEnum.eq,noticeTypeReqDTO.getType());
        if (noticeTypeMapper.listCount(entityWrapper)>0){
            throw ResultException.error(NoticeTypeErrorEnum.EXIST.getCode(),NoticeTypeErrorEnum.EXIST.getMsg());
        }
        noticeTypeReqDTO.setId(IDGenerateUtil.generateId());
        noticeTypeMapper.insert(noticeTypeReqDTO);
    }


    public void updateStatus(long id,short del) throws Exception {
        NoticeType noticeType=new NoticeType();
        noticeType.setType(del);
        noticeType.setId(id);
        noticeTypeMapper.updateById()
    }


    public List<NoticeType> listAllActive() {
        return noticeTypeMapper.listAllActive();
    }


    public List<NoticeType> listAll() {
        return noticeTypeMapper.listAll();
    }


    public void updateNoticeTypeName(NoticeType noticeTypeReqDTO) throws Exception {
        if (noticeTypeMapper.updateNoticeTypeName(noticeTypeReqDTO)>0){
            return;
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
    }


}
