package com.website.service;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.mybatis.entity.EntityWrapper;
import com.common.util.IDGenerateUtil;
import com.common.util.PageUtil;
import com.website.dao.NoticeMapper;
import com.website.entity.Notice;
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
        noticeReqDTO.setId(IDGenerateUtil.generateId());
        noticeMapper.insert(noticeReqDTO);
    }

    public boolean updateNotice(Notice noticeReqDTO){
        return noticeMapper.updateById(noticeReqDTO);
    }

    public boolean updateNoticePublish(long id){
        Notice notice=new Notice();
        notice.setId(id);
        notice.setPublish(0);
        return noticeMapper.updateById(notice);
    }

    public Notice getById(long id){
         return noticeMapper.queryById(id);
    }

    public boolean delNotice(long id) {
        return noticeMapper.deleteById(id);
    }

    public PageBean<Notice> listNoticeByPage(PageRequest pageRequest) {
        PageBean<Notice> pageBean = PageUtil.getPageBean(pageRequest.getPage_num(),pageRequest.getPage_size(), pageRequest.getOffset());
        EntityWrapper entityWrapper=new EntityWrapper();
        pageBean.setList(noticeMapper.listByPage(pageBean.getPage_num(),pageBean.getPage_size(),entityWrapper));
        pageBean.setTotal(noticeMapper.listCount(entityWrapper));
        return pageBean;
    }

    
    public Integer getCountByType(String type) {
        EntityWrapper entityWrapper=new EntityWrapper();
        return noticeMapper.listCount(entityWrapper);
    }
}
