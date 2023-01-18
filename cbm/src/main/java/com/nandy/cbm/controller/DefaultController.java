	package com.nandy.cbm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nandy.cbm.model.BodyInfo;
import com.nandy.cbm.model.RawMaterialWrapper;

@RestController
@RequestMapping(value = "/cbm")
public class DefaultController {
	private RestTemplate restTemplate;
	private HttpHeaders headers;
	HttpEntity<String> requestEntity;
	@Value("${application.rmm.url}")
	private String rmmUrl;
	
	public DefaultController() {
		this.restTemplate = new RestTemplate();
		this.headers = new HttpHeaders();
		headers.set("Content-Type", "application'/json");
		this.requestEntity = new HttpEntity<String>(headers);
	}

	@GetMapping("/getCarsWithBodyType/{carType}")
	public RawMaterialWrapper getCarsWithBodyType(@PathVariable String carType) throws Exception {
		
		if (rmmUrl == null)
			throw new Exception("Can't find RMM, please make sure you have set this in CBM props!!!		");
		
		//get raw material
		ResponseEntity<RawMaterialWrapper> resp = restTemplate.exchange(rmmUrl+"/get/"+carType, HttpMethod.GET, requestEntity, RawMaterialWrapper.class);
		
		return resp.getBody();
	}
	
	@GetMapping("/getCars")
	public RawMaterialWrapper getCars() {
		
		//get raw material
		ResponseEntity<RawMaterialWrapper> resp = restTemplate.exchange(rmmUrl+"/get", HttpMethod.GET, requestEntity, RawMaterialWrapper.class);
		
		return resp.getBody();
		
	}	
	
	@GetMapping("/test1")
	public RawMaterialWrapper test1() {
		//save raw material
		ResponseEntity<RawMaterialWrapper> resp = restTemplate.exchange(rmmUrl+"/createCar/testMake/testModel/testType/testThickness", HttpMethod.GET, requestEntity, RawMaterialWrapper.class);
		
		return resp.getBody();
	}
	
	@GetMapping("/test2")
	public RawMaterialWrapper test2() {
		//save raw material
		ResponseEntity<RawMaterialWrapper> resp = restTemplate.exchange(rmmUrl+"/createCar", HttpMethod.GET, requestEntity, RawMaterialWrapper.class);
		
		return resp.getBody();
	}

}
