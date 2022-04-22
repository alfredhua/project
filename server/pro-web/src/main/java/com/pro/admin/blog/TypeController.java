package com.pro.admin.blog;

import com.pro.admin.common.AdminBaseController;
import com.pro.admin.blog.vo.article.TypeUpdateStatusReqVO;
import com.pro.admin.blog.vo.type.TypeCreateReqVO;
import com.pro.admin.blog.vo.type.TypeListReqVO;
import com.pro.admin.blog.vo.type.TypeRespVO;
import com.pro.admin.blog.vo.type.TypeUpdateReqVO;
import com.blog.service.TypeService;
import com.common.domain.response.PageBean;
import com.common.util.BeanCopyUtil;
import com.pro.blog.dto.TypeListReqDTO;
import com.pro.blog.dto.entity.Type;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @auth guozhenhua
 * @date 2019/08/31
 */
@Api(tags = "文章类型管理")
@RestController
@RequestMapping(value = BlogUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class TypeController  extends AdminBaseController {

    @Autowired
    TypeService typeService;

    @ApiOperation(value = "文章类型创建")
    @RequestMapping(value = BlogUrl.CREATE_TYPE)
    public void createType(@RequestBody @Valid TypeCreateReqVO typeCreateRequest, BindingResult result){
         typeService.createType(BeanCopyUtil.copy(typeCreateRequest, Type.class));
    }

    @ApiOperation(value = "文章类型更改状态")
    @RequestMapping(value = BlogUrl.UPDATE_TYPE_STATUS)
    public void updateTypeStatus(@RequestBody @Valid TypeUpdateStatusReqVO typeUpdateRequest, BindingResult result) throws Exception {
         typeService.updateTypeStatus(typeUpdateRequest.getId(),typeUpdateRequest.getStatus());
    }

    @ApiOperation(value = "文章类型更新")
    @RequestMapping(value = BlogUrl.UPDATE_TYPE)
    public void updateType(@RequestBody @Valid TypeUpdateReqVO typeUpdateRequest, BindingResult result){
        Type typeReqDTO = BeanCopyUtil.copy(typeUpdateRequest, Type.class);
        typeService.updateType(typeReqDTO);
    }

    @ApiOperation(value = "获取文章类型")
    @RequestMapping(value = BlogUrl.GET_TYPE)
    public TypeRespVO getById(@PathVariable("id") long id){
        return resultReturn(typeService.getById(id),TypeRespVO.class);
    }

    @ApiOperation(value = "文章类型删除")
    @RequestMapping(value = BlogUrl.DEL_TYPE)
    public void delType(@PathVariable("id") long id) throws Exception {
         typeService.delType(id);
    }

    @ApiOperation(value = "文章类型列表")
    @RequestMapping(value = BlogUrl.LIST_TYPE)
    public PageBean<TypeRespVO> listTypeByPage(@RequestBody @Valid TypeListReqVO typeListReq, BindingResult result){
        TypeListReqDTO typeListReqDTO = BeanCopyUtil.copy(typeListReq, TypeListReqDTO.class);
       PageBean<Type> jsonResult = typeService.listTypeByPage(typeListReqDTO);
        return pageResultReturn(jsonResult,TypeRespVO.class);
    }

    @ApiOperation(value = "获取全部激活的文章类型")
    @RequestMapping(value = BlogUrl.LIST_ACTIVE_TYPE)
    public List<TypeRespVO> listAllActive(){
        return listReturn(typeService.listAllActive(),TypeRespVO.class);
    }

    @ApiOperation(value = "获取全部文章类型")
    @RequestMapping(value = BlogUrl.LIST_ALL)
    public List<TypeRespVO> listAll(){
        return listReturn(typeService.listAll(),TypeRespVO.class);
    }



}
