package com.message.service.sms.channel;

import com.common.api.entity.response.ResultResponse;
import com.common.util.*;
import com.message.constant.SmsChannelEnum;
import com.message.constant.SmsRecordStatusEnum;
import com.message.dao.SmsRecordMapper;
import com.message.dao.entity.SmsRecord;
import com.message.service.sms.domain.SmsInfo;
import com.message.service.sms.template.Template;
import com.pro.api.entity.message.constants.SmsTemplateEnum;
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
  protected abstract ResultResponse<Void> post(String phone, String params, SmsInfo smsInfo)throws Exception;


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
          return smsRecord(phone, GsonUtil.toJSONString(parms), smsInfo);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      return false;
    });

  }

  private boolean smsRecord(String phone,String params, SmsInfo smsInfo)throws Exception {
    SmsRecord smsRecord = saveSmsRecord(phone, smsInfo.getCode(), smsInfo.getContent(),getChannelType());
    LogUtil.info("短信开始发送："+smsInfo.getContent());
    if(!EnvUtil.isDevActive()) {
      ResultResponse<Void> result = post(phone, params, smsInfo);
      if (!ResultResponse.SUCCESS.equals(result.getCode())){
        smsRecord.setStatus(SmsRecordStatusEnum.FAIL.getStatus());
        smsRecord.setResult(getChannelType().getMsg()+"失败,"+result.getMsg());
        return smsRecordMapper.updateById(smsRecord);
      }
    }
    //开发环境下默认发送成功
    smsRecord.setStatus(SmsRecordStatusEnum.SUCCESS.getStatus());
    smsRecord.setResult(getChannelType().getMsg()+"成功");
    return smsRecordMapper.updateById(smsRecord);
  }

  private SmsRecord saveSmsRecord(String phone, String templateCode, String content, SmsChannelEnum channelType){
    SmsRecord record = new SmsRecord();
    record.setId(IDGenerateUtil.generateId());
    record.setNumbers(phone);
    record.setContent(content);
    record.setTemplate_code(templateCode);
    record.setStatus(SmsRecordStatusEnum.INIT.getStatus());
    record.setChannel_type(channelType.getType());
    smsRecordMapper.insert(record);
    return record;
  }


}
