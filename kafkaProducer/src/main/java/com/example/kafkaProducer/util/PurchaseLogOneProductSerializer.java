package com.example.kafkaProducer.util;

import com.example.kafkaProducer.vo.PurchaseLogOneProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class PurchaseLogOneProductSerializer implements Serializer<PurchaseLogOneProduct> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, PurchaseLogOneProduct data) {
        try {
            if (data == null) {
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("serializing....");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SecurityException("Error when serializing Message to byte[]");
        }
    }


    @Override
    public void close() {
        Serializer.super.close();
    }
}
