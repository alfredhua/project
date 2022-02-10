package com.website.dao;

import com.common.dao.annotation.DataAuth;
import com.website.dto.entity.Produce;
import com.website.dto.ProduceListReqDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
public interface ProduceMapper {


    String sql="id,title,pc_show,m_show,home_show,cover_image,file_list,ordering,introduce,auth_data_code,`context` ";

    @Select("select "+sql+" from m_website.site_produce where id=#{id} and del=0 ")
    Produce getById(@Param("id") String id);

    @Insert("insert into m_website.site_produce(id,title,pc_show,m_show,home_show,cover_image,file_list,ordering,introduce,auth_data_code)" +
            "values (#{id},#{title},#{pc_show},#{m_show},#{home_show},#{cover_image},#{file_list},#{ordering},#{introduce},#{auth_data_code})")
    void createProduce(Produce produceReqDTO);

    @UpdateProvider(type = ProduceProvider.class, method = "updateProduce")
    @DataAuth
    boolean updateProduce(Produce produceReqDTO);

    @Update("update m_website.site_produce set `del`=1 where id=#{id} and `del`=0 ")
    @DataAuth
    boolean delProduce(@Param("id") String id);

    @SelectProvider(type = ProduceProvider.class, method = "listProduceByPage")
    @DataAuth
    List<Produce> listProduceByPage(ProduceListReqDTO produceListReqDTO);

    @SelectProvider(type = ProduceProvider.class, method = "listProduceCount")
    @DataAuth
    Integer listProduceCount(ProduceListReqDTO produceListReqDTO);

    @SelectProvider(type = ProduceProvider.class, method = "listProduceHome")
    @DataAuth
    List<Produce> listProduceHome();

    @SelectProvider(type = ProduceProvider.class, method = "listAllProduce")
    @DataAuth
    List<Produce> listAllProduce();
}
