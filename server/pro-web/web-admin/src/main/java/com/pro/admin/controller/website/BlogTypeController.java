package com.pro.admin.controller.website;

import com.blog.entity.Type;
import com.blog.service.TypeService;
import com.common.api.entity.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.admin.controller.website.vo.article.TypeUpdateStatusReqVO;
import com.pro.admin.controller.website.vo.type.TypeCreateReqVO;
import com.pro.admin.controller.website.vo.type.TypeListReqVO;
import com.pro.admin.controller.website.vo.type.TypeRespVO;
import com.pro.admin.controller.website.vo.type.TypeUpdateReqVO;
import com.pro.api.entity.blog.TypeListReqDto;
import com.pro.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/08/31
 */
@Api(tags = "文章类型管理")
@RestController
@RequestMapping(value = WebsiteUrl.BLOG_BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class BlogTypeController extends BaseController {

    @Autowired
    TypeService typeService;

    @ApiOperation(value = "文章类型创建")
    @RequestMapping(value = WebsiteUrl.CREATE_TYPE)
    public void createType(@RequestBody @Valid TypeCreateReqVO typeCreateRequest ){
         typeService.createType(BeanCopyUtil.copy(typeCreateRequest, Type.class));
    }

    @ApiOperation(value = "文章类型更改状态")
    @RequestMapping(value = WebsiteUrl.UPDATE_TYPE_STATUS)
    public void updateTypeStatus(@RequestBody @Valid TypeUpdateStatusReqVO typeUpdateRequest ){
         typeService.updateTypeStatus(typeUpdateRequest.getId(),typeUpdateRequest.getStatus());
    }

    @ApiOperation(value = "文章类型更新")
    @RequestMapping(value = WebsiteUrl.UPDATE_TYPE)
    public void updateType(@RequestBody @Valid TypeUpdateReqVO typeUpdateRequest ){
        Type typeReqDTO = BeanCopyUtil.copy(typeUpdateRequest, Type.class);
        typeService.updateType(typeReqDTO);
    }

    @ApiOperation(value = "获取文章类型")
    @RequestMapping(value = WebsiteUrl.GET_TYPE)
    public TypeRespVO getById(@PathVariable("id") long id){
        return resultReturn(typeService.getById(id), TypeRespVO.class);
    }

    @ApiOperation(value = "文章类型删除")
    @RequestMapping(value = WebsiteUrl.DEL_TYPE)
    public void delType(@PathVariable("id") @NotNull long id) {
         typeService.delType(id);
    }

    @ApiOperation(value = "文章类型列表")
    @RequestMapping(value = WebsiteUrl.LIST_TYPE)
    public PageBean<TypeRespVO> listTypeByPage(@RequestBody @Valid TypeListReqVO typeListReq ){
        TypeListReqDto typeListReqDTO = BeanCopyUtil.copy(typeListReq, TypeListReqDto.class);
        PageBean<Type> jsonResult = typeService.listTypeByPage(typeListReqDTO);
        return pageResultReturn(jsonResult,TypeRespVO.class);
    }

    @ApiOperation(value = "获取全部激活的文章类型")
    @RequestMapping(value = WebsiteUrl.LIST_ACTIVE_TYPE)
    public List<TypeRespVO> listAllActive(){
        return listReturn(typeService.listAllActive(),TypeRespVO.class);
    }

    @ApiOperation(value = "获取全部文章类型")
    @RequestMapping(value = WebsiteUrl.LIST_ALL)
    public List<TypeRespVO> listAll(){
        return listReturn(typeService.listAll(),TypeRespVO.class);
    }



}
