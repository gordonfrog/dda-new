package com.nandy.srm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.nandy.srm.model.Car;
import com.nandy.srm.model.User;

@Service
public class KafkaConsumer {
	
	@Value("${kafka-topic}")
	private String topic;

    @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume1(String message) {
        System.out.println("Consume1 message on Kafka_Example: " + message);
    }

    @KafkaListener(topics = "test", groupId = "group_id")
    public void consume2(String message) {
        System.out.println("Consume2 message on test: " + message);
    }
    
    @KafkaListener(topics = "test")
    public void consume3(String message) {
        System.out.println("Consume3 message on test: " + message);
    }
    
    @KafkaListener(topics = "test")
    public void consume4(Car car) {
        System.out.println("Consume4 message on test: " + car.toString());
    }
    
    @KafkaListener(topics = "myTopic")
	public void processMessage(String content) {
	    System.out.println("process message: "+content);
	}

    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(User user) {
        System.out.println("Consumed JSON Message: " + user);
    }	
}
