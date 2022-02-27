package com.admin.web.controller.website;

import com.admin.web.controller.common.AdminBaseController;
import com.admin.web.controller.website.vo.noticetype.*;
import com.common.domain.response.PageBean;
import com.pro.website.dto.NoticeTypeListReqDTO;
import com.pro.website.dto.entity.NoticeType;
import com.website.service.NoticeTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.common.util.BeanCopyUtil.copy;

/**
 * @auth guozhenhua
 * @date 2018/12/20
 */
@Api(tags = "文章类型管理")
@RestController
@RequestMapping(value = WebsiteUrl.BASE_URL,method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
public class NoticeTypeController extends AdminBaseController {

    @Autowired
    NoticeTypeService noticeTypeService;

    @ApiOperation(value = "公告字典列表")
    @RequestMapping(value =WebsiteUrl.LIST_NOTICE_TYPE_BY_PAGE)
    public PageBean<NoticeTypeRespVO> listNoticeTypeByPage(@RequestBody @Valid NoticeTypeListReqVO noticeTypeListReqVO, BindingResult result)throws Exception {
        NoticeTypeListReqDTO noticeTypeListReqDTO = copy(noticeTypeListReqVO, NoticeTypeListReqDTO.class);
        PageBean<NoticeType> pageBean = noticeTypeService.listNoticeTypeByPage(noticeTypeListReqDTO);
        return pageResultReturn(pageBean, NoticeTypeRespVO.class);
    }

    @ApiOperation(value = "文章类型创建")
    @RequestMapping(value =WebsiteUrl.CREATE_NOTICE_TYPE)
    public void createNoticeType(@RequestBody @Valid NoticeTypeCreateReqVO noticeTypeCreateReqVO, BindingResult result) throws Exception {
        NoticeType noticeTypeReqDTO = copy(noticeTypeCreateReqVO, NoticeType.class);
        noticeTypeService.createNoticeType(noticeTypeReqDTO);
    }

    @ApiOperation(value = "文章类型更新")
    @RequestMapping(value =WebsiteUrl.UPDATE_NOTICE_TYPE)
    public void updateNoticeTypeName(@RequestBody @Valid NoticeTypeUpdateReqVO noticeTypeUpdateReqVO, BindingResult result) throws Exception {
        NoticeType noticeTypeReqDTO = copy(noticeTypeUpdateReqVO, NoticeType.class);
        noticeTypeService.updateNoticeTypeName(noticeTypeReqDTO);
    }

    @ApiOperation(value = "文章类型激活，关闭")
    @RequestMapping(value =WebsiteUrl.UPDATE_NOTICE_TYPE_STATUS)
    public void updateStatus(@RequestBody @Valid NoticeTypeActiveRequest noticeTypeActiveRequest, BindingResult result) throws Exception {
        noticeTypeService.updateStatus(noticeTypeActiveRequest.getId(),noticeTypeActiveRequest.getStatus());
    }

    @ApiOperation(value = "获取所有激活的类型")
    @RequestMapping(value =WebsiteUrl.LIST_ACTIVE_NOTICE_TYPE)
    public List<NoticeTypeRespVO> listAllActive(){
        return listReturn(noticeTypeService.listAllActive(), NoticeTypeRespVO.class);
    }

    @ApiOperation(value = "获取所有激活的类型")
    @RequestMapping(value =WebsiteUrl.LIST_ALL_NOTICE_TYPE)
    public List<NoticeTypeRespVO> listAll(){
        return listReturn(noticeTypeService.listAll(), NoticeTypeRespVO.class);
    }


}
