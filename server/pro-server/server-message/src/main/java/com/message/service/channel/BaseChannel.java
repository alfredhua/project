package com.message.service.channel;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.common.domain.response.JSONResult;
import com.common.util.*;
import com.pro.message.constants.SmsChannelEnum;
import com.pro.message.constants.SmsRecordStatusEnum;
import com.pro.message.constants.SmsTemplateEnum;
import com.message.dao.SmsRecordMapper;
import com.pro.message.dto.SmsInfo;
import com.pro.message.dto.entity.SmsRecord;
import com.message.service.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public abstract class BaseChannel {

  @Autowired
  SmsRecordMapper smsRecordMapper;

  /**
   * 不同渠道，模板的ID不一样
   * @return
   */
  protected abstract HashMap<SmsTemplateEnum, Template> getTemplateMap();

  /**
   * 渠道类型
   */
  public abstract SmsChannelEnum getChannelType();

  /**
   * 发送接口
   */
  protected abstract JSONResult<Void> post(String phone, String params, SmsInfo smsInfo)throws Exception;


  public boolean send(String phone, Map<String,String> params, SmsTemplateEnum templateType) throws Exception {
    Assert.hasText(phone, "phone not null");
    List<String> phones=new ArrayList<>();
    phones.add(phone);
    return send(phones, params ,getTemplateMap().get(templateType).parse(params));
  }

  public boolean send(List<String> phoneList, Map<String,String> params, SmsTemplateEnum templateType) throws Exception {
    return  send(phoneList,params,getTemplateMap().get(templateType).parse(params));
  }


  private boolean send(List<String> phoneList, Map<String,String> parms, SmsInfo smsInfo) throws Exception {
    if (phoneList==null||phoneList.isEmpty()){
      return false;
    }
    return  phoneList.stream().allMatch(phone -> {
      if (ValidateUtil.isValidPhone(phone)) {
        try {
          return smsRecord(phone, GsonUtils.toJSONString(parms), smsInfo);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      return false;
    });

  }

  private boolean smsRecord(String phone,String params, SmsInfo smsInfo)throws Exception {
    long id = saveSmsRecord(phone, smsInfo.getCode(), smsInfo.getContent(),getChannelType());
    LogUtils.info("短信开始发送："+smsInfo.getContent());
    if(!EnvUtils.isDevActive()) {
      JSONResult<Void> result = post(phone, params, smsInfo);
      if (JSONResult.SUCCESS.equals(result.getCode())){
        return smsRecordMapper.updateSmsRecordById(id, SmsRecordStatusEnum.SUCCESS.getStatus(), getChannelType().getMsg()+"成功",null );
      }
      return smsRecordMapper.updateSmsRecordById(id, SmsRecordStatusEnum.FAIL.getStatus(), getChannelType().getMsg()+"失败",result.getCode()+result.getMsg());
    }
    //开发环境下默认发送成功
    SendSmsResponse sendSmsResponse = new SendSmsResponse();
    sendSmsResponse.setCode("OK");
    return smsRecordMapper.updateSmsRecordById(id, SmsRecordStatusEnum.SUCCESS.getStatus(), getChannelType().getMsg()+"成功",null );
  }

  private long saveSmsRecord(String phone, String templateCode, String content, SmsChannelEnum channelType){
    SmsRecord record = new SmsRecord();
    record.setId(IDGenerate.generateId());
    record.setNumbers(phone);
    record.setContent(content);
    record.setTemplate_code(templateCode);
    record.setStatus(SmsRecordStatusEnum.INIT.getStatus());
    record.setChannel_type(channelType.getType());
    smsRecordMapper.insert(record);
    return record.getId();
  }


}
