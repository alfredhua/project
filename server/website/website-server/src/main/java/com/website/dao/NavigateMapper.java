package com.website.dao;

import com.website.dto.NavigateListReqDTO;
import com.website.dto.entity.Navigate;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2021/08/23
 */
public interface NavigateMapper {

    String sql=" `id`,`type`,`title`,`icon`,`introduce`,`create_time`,`update_time`,`del` ";

    @Select("select "+sql+" from m_website.site_navigate where id=#{id} and `del`=0 ")
    Navigate getById(@Param("id") long id);

    @Update("update m_website.site_navigate set `del`=1 where id=#{id} and `del`=0 ")
    boolean delNavigate(@Param("id") long id);

    @UpdateProvider(type = NavigateProvider.class, method = "createNavigate")
    void createNavigate(Navigate navigate);

    @UpdateProvider(type = NavigateProvider.class, method = "updateNavigate")
    boolean updateNavigate(Navigate navigate);

    @SelectProvider(type = NavigateProvider.class, method = "listNavigateByPage")
    List<Navigate> listNavigateByPage(NavigateListReqDTO navigateListReqDTO);

    @SelectProvider(type = NavigateProvider.class, method = "listNavigateCount")
    Integer listNavigateCount(NavigateListReqDTO navigateListReqDTO);

}
