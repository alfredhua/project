package com.website.dao;

import com.common.dao.annotation.DataAuth;
import com.website.dto.entity.Partner;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
public interface PartnerMapper {

    String sql=" `id`,`name`,`href`,`pic_url`,`del`,`create_time`,`update_time`,auth_data_code ";

    @Select("select "+sql+" from m_website.site_partner where id=#{id} and `del`=0 ")
    @DataAuth
    Partner getById(@Param("id") String id);

    @Insert("insert into  m_website.site_partner(`id`,`name`,`href`,`pic_url`,`auth_data_code`)" +
            "values (#{id},#{name},#{href},#{pic_url},#{auth_data_code})")
    void createPartner(Partner partnerReqDTO);

    @UpdateProvider(type = PartnerProvider.class, method = "updatePartner")
    @DataAuth
    boolean updatePartner(Partner partnerReqDTO);

    @Update("update m_website.site_partner set `del`=1 where id=#{id} and `del`=0 ")
    @DataAuth
    boolean delPartner(@Param("id") String id);

    @SelectProvider(type = PartnerProvider.class, method = "listPartnerByPage")
    @DataAuth
    List<Partner> listPartnerByPage(Integer offset,Integer page_size);

    @SelectProvider(type = PartnerProvider.class, method = "listPartnerCount")
    @DataAuth
    Integer listPartnerCount();

    @SelectProvider(type = PartnerProvider.class, method = "listAllPartner")
    @DataAuth
    List<Partner> listAllPartner();

}
