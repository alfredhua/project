package com.pro.site.controller;

import com.common.aspect.annotation.LimitTime;
import com.pro.controller.BaseController;
import com.pro.site.controller.vo.produce.ProduceResponseVO;
import com.website.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author guozhenhua
 * @date 2019/07/14
 */
@RestController
@RequestMapping(value = SiteWebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class SiteProduceController extends BaseController {

    @Autowired
    ProduceService produceService;

    @RequestMapping(value = SiteWebsiteUrl.LIST_PRODUCE_HOME)
    @LimitTime
    public List<ProduceResponseVO> listProduceHome(){
        return listReturn(produceService.listProduceHome(),ProduceResponseVO.class);
    }

    @RequestMapping(value = SiteWebsiteUrl.LIST_ALL_PRODUCE)
    @LimitTime
    public List<ProduceResponseVO> listAllProduce(){
        return listReturn(produceService.listAllProduce(),ProduceResponseVO.class);
    }

    @RequestMapping(value = SiteWebsiteUrl.GET_PRODUCE)
    @LimitTime
    public ProduceResponseVO getById(@PathVariable("id") long id){
        return resultReturn(produceService.getById(id),ProduceResponseVO.class);
    }

}
