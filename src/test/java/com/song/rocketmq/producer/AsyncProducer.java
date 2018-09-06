package com.song.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Created by Song on 2018/08/27.
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        // 创建生产者
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();

        producer.setRetryTimesWhenSendAsyncFailed(0);

        Message message = new Message("song", "tag_1", "user_id_1", "user_1".getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("success:" + sendResult.getMsgId() + "!");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("exception:" + message.getKeys() + "!");
            }
        });

        producer.shutdown();
    }
}
