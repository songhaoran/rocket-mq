package com.song.rocketmq.controller;

import com.song.rocketmq.service.BrodcastProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Song on 2018/08/28.
 */
@RestController
public class TestMqController {

    @Resource
    private BrodcastProducerService brodcastProducerService;

    @GetMapping(value = "/broadcast/producer")
    public String produce(@RequestParam(value = "msg_body") String msgBody) throws Exception{
        return brodcastProducerService.sendMsg(msgBody);
    }

}
