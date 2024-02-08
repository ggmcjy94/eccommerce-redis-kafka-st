package com.example.kafkaProducer.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Map;

//구매를 한 이력
@Data
public class PurchaseLog {

    // SAMPLE DATA
    // {"orderId": "od-0005", "userId": "uid-0005",  "productInfo": [{"productId": "pg-0023", "price":"12000"}, {"productId":"pg-0022", "price":"13500"}],  "purchasedDt": "20230201070000",  "price": 24000}

    String orderId; //  od-0001
    String userId; // uid-0001
    ArrayList<Map< String, String>> productInfo; // [ {"productid":"pg-0001", "price":"24000"}, {}... ]
    String purchasedDt;// 20230201070000
    String price;//240000

}
