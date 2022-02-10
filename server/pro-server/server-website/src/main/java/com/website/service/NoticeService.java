package com.website.service;

import com.common.domain.constants.SysErrorCodeEnum;
import com.common.domain.exception.ResultException;
import com.common.domain.response.PageBean;
import com.common.util.IDGenerate;
import com.common.util.PageUtil;
import com.website.dao.NoticeMapper;
import com.pro.website.dto.entity.Notice;
import com.pro.website.dto.NoticeListReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auth guozhenhua
 * @date 2018/12/25
 */
@Component
public class NoticeService  {

    @Autowired
    NoticeMapper noticeMapper;


    public void createNotice(Notice noticeReqDTO) {
        noticeReqDTO.setId(IDGenerate.generateId());
        noticeMapper.createNotice(noticeReqDTO);
    }

    public void updateNotice(Notice noticeReqDTO) throws Exception {
        if(!noticeMapper.updateNotice(noticeReqDTO)){
            throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
        }
    }

    public void updateNoticePublish(long id) throws Exception {
        if(!noticeMapper.updateNoticePublish(id)){
            throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
        }
    }

    public Notice getById(long id) throws ResultException {
         Notice notice = noticeMapper.getById(id);
        if (notice==null){
            throw  ResultException.error(SysErrorCodeEnum.EMPTY);
        }
        return notice;
    }

    public void delNotice(long id) throws Exception {
        if(!noticeMapper.delNotice(id)){
            throw ResultException.error(SysErrorCodeEnum.SAVE_ERROR);
        }
    }

    public PageBean<Notice> listNoticeByPage(NoticeListReqDTO noticeListReqDTO) {
        PageBean<Notice> pageBean = PageUtil.validatePage(noticeListReqDTO.getPage_num(),
                noticeListReqDTO.getPage_size(), noticeListReqDTO.getOffset());
        pageBean.setList(noticeMapper.listNoticeByPage(noticeListReqDTO));
        pageBean.setTotal(noticeMapper.listNoticeCount(noticeListReqDTO));
        return pageBean;
    }

    
    public Integer getCountByType(String type) {
        return noticeMapper.getCountByType(type);
    }
}
