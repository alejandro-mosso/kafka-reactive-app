package com.stock.exchange.service.impl;


import com.stock.exchange.service.KafkaService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaServiceImpl implements KafkaService {


    private Flux<ReceiverRecord<String, String>> topicStream;


    public KafkaServiceImpl() throws IOException {

        Properties kafkaProperties = PropertiesLoaderUtils.loadAllProperties("ccloud.properties");

        kafkaProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "reactive-consumer");
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "reactive-consumer-group");
        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.create(kafkaProperties);

        topicStream = this.createTopicCache(receiverOptions, "stock-value-out");
    }


    @Override
    public Flux<ReceiverRecord<String, String>> getTopicFlux() {

        return topicStream;
    }

    private <T, G> Flux<ReceiverRecord<T, G>> createTopicCache(ReceiverOptions<T, G> receiverOptions, String topicName) {
        ReceiverOptions<T, G> options = receiverOptions.subscription(Collections.singleton(topicName));

        return KafkaReceiver.create(options).receive().cache();
    }
}
