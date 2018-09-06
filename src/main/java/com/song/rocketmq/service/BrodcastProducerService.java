package com.song.rocketmq.service;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Song on 2018/08/28.
 */
@Service
public class BrodcastProducerService {

    @Resource
    private DefaultMQProducer producer;

    @Value(value = "${rocketmq.sync.user.topic}")
    private String topic;

    @Value(value = "${rocketmq.sync.user.tag}")
    private String tag;

    public String sendMsg(String msgBody) throws Exception{
        Message message = new Message(topic, tag, msgBody.getBytes(RemotingHelper.DEFAULT_CHARSET));
        SendResult sendResult = producer.send(message);
        return sendResult.toString();
    }
}
