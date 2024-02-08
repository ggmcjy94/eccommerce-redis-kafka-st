package com.example.kafkaProducer.vo;

//광고를 본 이력

import lombok.Data;

@Data
public class WatchingAdLog {

    // SAMPLE DATA
    // {"userId": "uid-0005", "productId": "pg-0022", "adId": "ad-101", "adType": "banner", "watchingTime": "30", "watchingDt": "20230201070000"}
    String userId; // uid-0001
    String productId; // pg-0001
    String adId; //  ad-101 광고 id
    String adType; // banner, clip, main, live
    String watchingTime; // 머문시간
    String watchingDt; // 20230201070000



}
