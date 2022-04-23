package com.website.dao;

import com.common.mybatis.BaseMapper;
import com.website.entity.News;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auth guozhenhua
 * @date 2019/07/12
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {
}
