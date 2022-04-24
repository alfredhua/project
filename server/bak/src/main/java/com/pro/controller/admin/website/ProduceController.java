package com.pro.controller.admin.website;

import com.pro.controller.admin.website.vo.produce.ProduceCreateReqVO;
import com.pro.controller.admin.website.vo.produce.ProduceListReqVO;
import com.pro.controller.admin.website.vo.produce.ProduceRespVO;
import com.pro.controller.admin.website.vo.produce.ProduceUpdateReqVO;
import com.pro.controller.admin.website.vo.producedetail.ProduceDetailUpdateReqVO;
import com.pro.controller.common.AdminBaseController;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.website.dto.ProduceListReqDTO;
import com.pro.website.dto.entity.Produce;
import com.website.service.ProduceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @auth guozhenhua
 * @date 2019/07/08
 */
@Api(tags = "产品管理")
@RestController
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class ProduceController  extends AdminBaseController {


    @Autowired
    ProduceService produceService;

    @ApiOperation(value = "产品创建")
    @RequestMapping(value = WebsiteUrl.CREATE_PRODUCE)
    public void createProduce(@RequestBody @Valid ProduceCreateReqVO produceCreateReqVO ){
        Produce produceReqDTO = BeanCopyUtil.copy(produceCreateReqVO, Produce.class);
        produceService.createProduce(produceReqDTO);
    }

    @ApiOperation(value = "产品更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_PRODUCE)
    public void updateProduce(@RequestBody @Valid ProduceUpdateReqVO produceUpdateReqVO ) throws Exception {
        Produce produceReqDTO = BeanCopyUtil.copy(produceUpdateReqVO, Produce.class);
        produceService.updateProduce(produceReqDTO);
    }

    @ApiOperation(value = "产品获取")
    @RequestMapping(value = WebsiteUrl.GET_PRODUCE)
    public ProduceRespVO getById(@PathVariable("id") String id){
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
    public void delProduce(@PathVariable("id") String id) throws Exception {
        produceService.delProduce(id);
    }


    @ApiOperation(value = "产品列表")
    @RequestMapping(value = WebsiteUrl.LIST_PRODUCE)
    public PageBean<ProduceRespVO> listProduceByPage(@RequestBody @Valid ProduceListReqVO produceListReqVO ){
        ProduceListReqDTO produceListReqDTO = BeanCopyUtil.copy(produceListReqVO, ProduceListReqDTO.class);
        PageBean<Produce> pageBean = produceService.listProduceByPage(produceListReqDTO);
        return pageResultReturn(pageBean,ProduceRespVO.class);
    }

}
