	package com.nandy.rmm.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.nandy.rmm.config.KakfaConfiguration;
import com.nandy.rmm.model.BodyInfo;
import com.nandy.rmm.model.Car;
import com.nandy.rmm.model.EngineInfo;
import com.nandy.rmm.model.RawMaterialWrapper;
import com.nandy.rmm.model.User;
import com.nandy.rmm.service.RMMService;

@RestController
@RequestMapping("/rmm")
public class RMMController {
	
//	@Value("${kafka-topic}")
//	private String topic;
//	
//	@Autowired
//	private KakfaConfiguration kafkaConfig;
	
	@Autowired
	private RMMService service;
	
	@PostMapping("/create") //create raw material
	public void create(@RequestBody BodyInfo rawData) {
		service.create(rawData);
	}
	
	@GetMapping("/createCar/{carMake}/{carModel}/{cartType}/{carThickness}")
	public ResponseEntity<RawMaterialWrapper> saveCar(@PathVariable String carMake, @PathVariable String carModel, @PathVariable String carType, @PathVariable String carThickness) {
		EngineInfo engine = new EngineInfo();
		engine.setMake(carMake);
		engine.setModel(carModel);
		engine.setType(carType);
		BodyInfo rawData = new BodyInfo(carType, carThickness, Arrays.asList(engine));
		//save car
		RawMaterialWrapper ret = new RawMaterialWrapper(service.create(rawData));
		return new ResponseEntity<RawMaterialWrapper>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/createCar")
	public ResponseEntity<RawMaterialWrapper> saveCar() {
		EngineInfo engine = new EngineInfo();
		engine.setMake("default");
		engine.setModel("default");
		engine.setType("default");
		BodyInfo rawData = new BodyInfo("default", "	", Arrays.asList(engine));
		//save car
		RawMaterialWrapper ret = new RawMaterialWrapper(service.create(rawData));
		return new ResponseEntity<RawMaterialWrapper>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/get/{type}")
	public ResponseEntity<RawMaterialWrapper> get(@PathVariable String type) {
		List<BodyInfo> result =  service.get(type);
		RawMaterialWrapper ret = new RawMaterialWrapper(result);
		Car car = new Car();
		car.setColor("black");
		car.setModel("bmw");
		car.setWrapper(ret);
		//kafkaConfig.kafkaTemplate3().send(topic,car);
		return new ResponseEntity<RawMaterialWrapper>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<RawMaterialWrapper> getAll() {
		List<BodyInfo> result = service.getAll();
		RawMaterialWrapper ret = new RawMaterialWrapper(result);
		return new ResponseEntity<RawMaterialWrapper>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/produce1/{item}")
	public String produce1(@PathVariable String item) {
		User u = new User(item, item, 1L);
		//kafkaConfig.kafkaTemplate1().send("Kafka_Example",u);
		return "successfully sent";
	}
	
	@GetMapping("/produce2/{item}")
	public String produce2(@PathVariable String item) {
		//kafkaConfig.kafkaTemplate2().send(topic,item);
		return "successfully sent";
	}

}
