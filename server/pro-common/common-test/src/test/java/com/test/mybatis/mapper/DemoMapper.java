package com.test.mybatis.mapper;

import com.common.mybatis.BaseMapper;
import com.test.entity.CommonTest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper extends BaseMapper<CommonTest> {

}
