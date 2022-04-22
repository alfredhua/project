package com.common.rabbitmq.consumer;

public abstract class AbstractMqConsumer<T> {

    /**
     * 设置topic
     * @return
     */
    public abstract String getTopic();

    /**
     * 消费MQ
     * @return
     */
    public abstract String consume(T t);

    /**
     * 是否自动应答
     * @return
     */
    public boolean autoAck(){
        return true;
    }



}
