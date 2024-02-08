package com.example.kafkaProducer.service;

import com.example.kafkaProducer.config.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service

public class Producer {

    @Autowired
    KafkaConfig myConfig;


    String topicName = "fastcampus";

    private KafkaTemplate<String, Object> kafkaTemplate;



    public void pub(String msg) {
        kafkaTemplate = myConfig.kafkaTemplateForGeneral();
        kafkaTemplate.send(topicName, msg);
    }

    public void sendJoinedMsg(String topicNm, Object msg) {
        kafkaTemplate = myConfig.kafkaTemplateForGeneral();
        kafkaTemplate.send(topicNm, msg);
    }

    public void sendMsgForPurchaseLog(String topicNm, Object msg) {
        kafkaTemplate = myConfig.KafkaTemplateForWatchingAdLog();
        kafkaTemplate.send(topicNm, msg);
    }

    public void sendMsgForWatchingAdLog(String topicNm, Object msg) {
        kafkaTemplate = myConfig.KafkaTemplateForPurchaseLog();
        kafkaTemplate.send(topicNm, msg);
    }
}
