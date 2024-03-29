package com.example.kafkaProducer.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service

public class StreamService {

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    public void buildPipeline(StreamsBuilder sb) {
//        KStream<String, String> myStream  = sb.stream("fastcampus", Consumed.with(STRING_SERDE,STRING_SERDE));
//        myStream.print(Printed.toSysOut());
//        myStream.filter((key, value) -> value.contains("freeClass")).to("freeClassList");


//        KStream<String, String> leftStream = sb
//                .stream("leftTopic", Consumed.with(STRING_SERDE, STRING_SERDE));
//
//
//        // key:value --> 1:leftValue
//        KStream<String, String> rightStream = sb
//                .stream("rightTopic", Consumed.with(STRING_SERDE, STRING_SERDE));
//        // key:value --> 1:rightValue
//
//        leftStream.print(Printed.toSysOut());
//        rightStream.print(Printed.toSysOut());
//
//        ValueJoiner<String, String , String> stringJoiner = (leftValue, rightValue)
//                -> "[StringJoiner]" + leftValue + "-" + rightValue;
//
//        ValueJoiner<String, String , String> stringOuterJoiner = (leftValue, rightValue)
//                -> "[StringOuterJoiner]" + leftValue + "<" + rightValue;
//
//        KStream<String, String> joinedStream = leftStream.join(rightStream,
//                stringJoiner,
//                JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofSeconds(10)));
//
//        KStream<String, String> outerJoinedStream = leftStream.outerJoin(rightStream,
//                stringOuterJoiner,
//                JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofSeconds(10)));
//
//        joinedStream.print(Printed.toSysOut());
//        joinedStream.to("joinedMsg");
//        outerJoinedStream.to("joinedMsg");
    }
}
