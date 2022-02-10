package com.pro.message.constants;

public enum SmsChannelEnum {

        ALI((short)1,"阿里");

        short type;
        String msg;

        SmsChannelEnum(short type, String msg) {
            this.type = type;
            this.msg=msg;
        }

        public short getType() {
            return type;
        }

        public String getMsg() {
            return msg;
        }
    }