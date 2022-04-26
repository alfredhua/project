package com.pro.controller.site.blog;

import com.blog.service.TypeService;
import com.common.aspect.annotation.LimitTime;
import com.common.domain.response.JSONResult;
import com.site.controller.blog.vo.type.TypeReqVO;
import com.site.controller.blog.vo.type.TypeRespVO;
import com.site.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/08/29
 */
@RestController
@RequestMapping(value = BlogUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class TypeController  extends BaseController {


    @Autowired
    TypeService typeService;

    /**
     * 获取全部
     */
    @RequestMapping(value = BlogUrl.LIST_ALL_TYPE)
    @LimitTime
    public List<TypeRespVO> listAllActive(){
        return listReturn(typeService.listAllActive(),TypeRespVO.class);
    }


    /**
     * 获取
     */
    @RequestMapping(value = BlogUrl.GET_TYPE)
    @LimitTime
    public TypeRespVO getById(@RequestBody TypeReqVO typeReqVO){
        return resultReturn(typeService.getByType(typeReqVO.getType()),TypeRespVO.class);
    }



}
