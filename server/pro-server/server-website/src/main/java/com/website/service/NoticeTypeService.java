package com.website.service;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.api.exception.ResultException;
import com.common.mybatis.entity.EntityWrapper;
import com.common.mybatis.enums.ConditionEnum;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.website.constant.NoticeTypeErrorEnum;
import com.website.dao.NoticeTypeMapper;
import com.website.entity.NoticeType;
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

    public PageBean<NoticeType> listNoticeTypeByPage(PageRequest pageRequest) {
        PageBean<NoticeType> pageBean = PageUtil.getPageBean(pageRequest.getPage_num(),pageRequest.getPage_size(), pageRequest.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(noticeTypeMapper.listByPage(pageBean.getPage_num(), pageBean.getPage_size(),entityWrapper));
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


    public void updateStatus(long id,short del){
        NoticeType noticeType=new NoticeType();
        noticeType.setDel(del);
        noticeType.setId(id);
        noticeTypeMapper.updateById(noticeType);
    }


    public List<NoticeType> listAllActive() {
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.addCondition("status",ConditionEnum.eq,(short)1);
        return noticeTypeMapper.listAll(entityWrapper);
    }


    public List<NoticeType> listAll() {
        EntityWrapper entityWrapper=new EntityWrapper();
        return noticeTypeMapper.listAll(entityWrapper);
    }

    public boolean updateNoticeTypeName(NoticeType noticeTypeReqDTO) {
        return noticeTypeMapper.updateById(noticeTypeReqDTO);
    }

}
