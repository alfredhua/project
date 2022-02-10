package com.common.middle.mq;

public abstract class AbstractMqConsumer<T> {

    /**
     * 设置topic
     * @return
     */
    abstract String getTopic();

    /**
     * 消费MQ
     * @return
     */
    abstract String consume(T t);

    /**
     * 是否自动应答
     * @return
     */
    public boolean autoAck(){
        return true;
    }

}
