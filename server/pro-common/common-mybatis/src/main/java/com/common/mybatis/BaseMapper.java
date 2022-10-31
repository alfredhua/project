package com.common.mybatis;

import com.common.mybatis.entity.EntityWrapper;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Set;

@Mapper
public interface BaseMapper<T> {

    /**
     * 插入
     * @param t 插入实体
     */
    @Insert("")
    void insert(T t);

    /**
     * 更新
     * @param t 更新实体
     * @return 返回结果
     */
    @Update("")
    boolean updateById(T t);

    /**
     * 查询
     * @param id id
     * @return 返回结果
     */
    @Select("")
    T queryById(Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("")
    boolean deleteById(Long id);

    /**
     * 批量查询
     * @param ids
     * @return
     */
    @Select("")
    List<T> listByIds(@Param("ids") Set<Long> ids);


    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Delete("")
    boolean deleteByIds(@Param("ids") Set<Long> ids);

    /**
     * 分页查询
     * @return
     */
    @Select("")
    List<T> listByPage(@Param("page") int page,@Param("pageSize") int pageSize,@Param("entityWrapper") EntityWrapper entityWrapper);

    /**
     * 查询
     * @return
     */
    @Select("")
    List<T> listAll(@Param("entityWrapper") EntityWrapper entityWrapper);

    /**
     * 查询个数
     * @param entityWrapper
     * @return
     */
    @Select("")
    int listCount(@Param("entityWrapper") EntityWrapper entityWrapper);
}
