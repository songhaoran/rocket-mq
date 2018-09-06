package com.song.rocketmq.producer;



import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;


/**
 * Created by Song on 2018/08/28.
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("test_group");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();
        Message message = new Message("song", "tag_2", "song".getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult sendResult = producer.send(message);

        producer.shutdown();
    }
}
