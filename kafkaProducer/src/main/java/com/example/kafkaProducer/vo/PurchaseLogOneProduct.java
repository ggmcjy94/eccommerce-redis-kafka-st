package com.example.kafkaProducer.vo;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

@Data
public class PurchaseLogOneProduct {


    String orderId; //  od-0001
    String userId; // uid-0001
    String productId; // pg-0001
    String purchasedDt; // 20230201070000
    String price; // 24000
}
