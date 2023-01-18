//package com.nandy.rmm.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.nandy.rmm.config.KakfaConfiguration;
//import com.nandy.rmm.model.Car;
//
//@Service
//public class CarProducerService {
//	
//	@Value("${kafka-topic}")
//	private String topic;
//	
//	@Autowired
//	private KakfaConfiguration kafkaConfig;
//	
//	public void send(Car car) {
//		kafkaConfig.kafkaTemplate3().send(topic, car);
//	}
//
//}
