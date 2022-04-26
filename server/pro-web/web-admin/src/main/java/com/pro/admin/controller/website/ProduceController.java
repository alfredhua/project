package com.pro.admin.controller.website;

import com.common.api.entity.request.PageRequest;
import com.common.api.entity.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.admin.controller.website.vo.produce.*;
import com.pro.controller.common.BaseController;
import com.website.entity.Produce;
import com.website.service.ProduceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Api(tags = "产品管理")
@RestController
@RequestMapping(value = WebsiteUrl.WEBSITE_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class ProduceController  extends BaseController {


    @Autowired
    ProduceService produceService;

    @ApiOperation(value = "产品创建")
    @RequestMapping(value = WebsiteUrl.CREATE_PRODUCE)
    public void createProduce(@RequestBody @Valid ProduceCreateReqVO produceCreateReqVO ){
        produceService.createProduce(BeanCopyUtil.copy(produceCreateReqVO, Produce.class));
    }

    @ApiOperation(value = "产品更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_PRODUCE)
    public void updateProduce(@RequestBody @Valid ProduceUpdateReqVO produceUpdateReqVO ) throws Exception {
        Produce produceReqDTO = BeanCopyUtil.copy(produceUpdateReqVO, Produce.class);
        produceService.updateProduce(produceReqDTO);
    }

    @ApiOperation(value = "产品获取")
    @RequestMapping(value = WebsiteUrl.GET_PRODUCE)
    public ProduceRespVO getById(@PathVariable("id") long id){
        return resultReturn(produceService.getById(id),ProduceRespVO.class);
    }

    @ApiOperation(value = "产品详情更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_PRODUCE_DETAIL)
    public void updateProduceDetail(@RequestBody @Valid ProduceDetailUpdateReqVO produceDetailUpdateReqVO ) throws Exception {
        Produce produce = BeanCopyUtil.copy(produceDetailUpdateReqVO, Produce.class);
        produce.setUpdate_time(LocalDateTime.now());
        produceService.updateProduce(produce);
    }


    @ApiOperation(value = "产品删除")
    @RequestMapping(value = WebsiteUrl.DEL_PRODUCE)
    public void delProduce(@PathVariable("id") long id) throws Exception {
        produceService.delProduce(id);
    }


    @ApiOperation(value = "产品列表")
    @RequestMapping(value = WebsiteUrl.LIST_PRODUCE)
    public PageBean<ProduceRespVO> listProduceByPage(@RequestBody @Valid ProduceListReqVO produceListReqVO ){
        return pageResultReturn(
                produceService.listProduceByPage(new PageRequest(produceListReqVO.getPage_num(),produceListReqVO.getPage_size())),
                ProduceRespVO.class);
    }

}
