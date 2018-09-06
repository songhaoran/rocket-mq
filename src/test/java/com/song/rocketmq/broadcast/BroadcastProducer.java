package com.song.rocketmq.broadcast;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Created by Song on 2018/08/28.
 */
public class BroadcastProducer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("test-group");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();

        Message message = new Message("song", "tag_broad_cast", "宋浩然".getBytes(RemotingHelper.DEFAULT_CHARSET));
        SendResult sendResult = producer.send(message);
        producer.shutdown();
    }
}
