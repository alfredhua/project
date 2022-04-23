package com.website.dao;

import com.common.mybatis.BaseMapper;
import com.website.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auth guozhenhua
 * @date 2018/12/28
 */

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
