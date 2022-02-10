package com.website.service;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.IDGenerate;
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
        PageBean<NoticeType> pageBean = PageUtil.validatePage(noticeTypeListReqDTO.getPage_num(),
                noticeTypeListReqDTO.getPage_size(), noticeTypeListReqDTO.getOffset());
        pageBean.setList(noticeTypeMapper.listNoticeTypeByPage(pageBean.getOffset(), pageBean.getPage_size()));
        pageBean.setTotal(noticeTypeMapper.listNoticeTypeByPageCount());
        return pageBean;
    }

    
    public void createNoticeType(NoticeType noticeTypeReqDTO) throws Exception {
        if (noticeTypeMapper.getByType(noticeTypeReqDTO.getType())>0){
            throw ResultException.error(NoticeTypeErrorEnum.EXIST.getCode(),NoticeTypeErrorEnum.EXIST.getMsg());
        }
        noticeTypeReqDTO.setId(IDGenerate.generateId());
        noticeTypeMapper.createNoticeType(noticeTypeReqDTO);
    }

    
    public void updateStatus(long id,short del) throws Exception {
        if (NoticeTypeActiveEnum.ACTIVE.getCode()==del){
            if (noticeTypeMapper.updateStatus(id,NoticeTypeActiveEnum.ACTIVE.getCode())>0){
                return;
            }
        }else{
            if (noticeTypeMapper.updateStatus(id,NoticeTypeActiveEnum.NOT_ACTIVE.getCode())>0){
                return;
            }
        }
        throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
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
