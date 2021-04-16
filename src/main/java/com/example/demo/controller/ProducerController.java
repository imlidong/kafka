package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date
 */
@RestController
public class ProducerController {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping("message/send/{msg}")
    public String send(@PathVariable String msg) {
        kafkaTemplate.send("demo", msg);        //使用kafka模板发送信息
        return "success";
    }
}