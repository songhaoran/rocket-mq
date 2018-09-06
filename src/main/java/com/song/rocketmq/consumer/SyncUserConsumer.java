package com.song.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Song on 2018/08/28.
 */
@Configuration
public class SyncUserConsumer {
    @Value(value = "${rocketmq.sync.user.topic}")
    private String topic;

    @Value(value = "${rocketmq.sync.user.tag}")
    private String tag;

    @Value(value = "${spring.rocketmq.name-server}")
    private String namesrvAddr;

    @Value(value = "${spring.rocketmq.producer-group-name}")
    private String groupName;


    @Bean
    public DefaultMQPushConsumer getSyncUserConsumer() throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.subscribe(topic,tag);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                // TODO: 2018/8/28 处理业务

                if (list != null && list.size() >0) {
                    MessageExt messageExt = list.get(0);
                    if (messageExt != null) {
                        try {
                            System.out.println("consumer:"+new String(messageExt.getBody(),RemotingHelper.DEFAULT_CHARSET));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        return consumer;
    }

}
