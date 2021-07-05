package com.site.controller.website;

import com.common.aspect.annotation.LimitTime;
import com.common.domain.response.JSONResult;
import com.site.controller.common.BaseController;
import com.site.controller.website.vo.produce.ProduceResponseVO;
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
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class ProduceController extends BaseController {

    @Autowired
    ProduceService produceService;

    @RequestMapping(value = WebsiteUrl.LIST_PRODUCE_HOME)
    @LimitTime
    public List<ProduceResponseVO> listProduceHome(){
        return listReturn(produceService.listProduceHome(),ProduceResponseVO.class);
    }

    @RequestMapping(value = WebsiteUrl.LIST_ALL_PRODUCE)
    @LimitTime
    public List<ProduceResponseVO> listAllProduce(){
        return listReturn(produceService.listAllProduce(),ProduceResponseVO.class);
    }

    @RequestMapping(value = WebsiteUrl.GET_PRODUCE)
    @LimitTime
    public ProduceResponseVO getById(@PathVariable("id") String id){
        return resultReturn(produceService.getById(id),ProduceResponseVO.class);
    }

}
