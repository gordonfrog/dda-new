package com.sh.listener.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.events.producer.MsgProducer;
import com.sh.events.publisher.EventsPublisher;
import com.sh.listener.RabbitListener;
import com.sh.messages.CustomerMsg;
import com.sh.messages.ShopMsg;
import com.sh.model.RawMaterialWrapper;

/**
 * 
 * @author shoe011
 *
 */
@Component
public class ListenerCustomer implements RabbitListener<CustomerMsg>{

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerCustomer.class);
    private RestTemplate restTemplate;
	private HttpHeaders headers;
	HttpEntity<String> requestEntity;
	@Value("${application.rmm.url}")
	private String rmmUrl;
    
    @Autowired
    private EventsPublisher publisher;
    
    /**
	 * @see MsgProducer
	 */
	@Autowired
	private MsgProducer producer;
    
    @Override
    public void receiveMessage(CustomerMsg message) {
    	
    	this.restTemplate = new RestTemplate();
		this.headers = new HttpHeaders();
		headers.set("Content-Type", "application'/json");
		this.requestEntity = new HttpEntity<String>(headers);
    	
    	try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);			
			LOGGER.debug("Receive Nessage: \n"+json);
			
			if (json.contains("1")) {
					//see if we have the raw materials
					//ResponseEntity<RawMaterialWrapper> resp = restTemplate.exchange(rmmUrl+"/get", HttpMethod.GET, requestEntity, RawMaterialWrapper.class);
					ShopMsg msg = new ShopMsg("Gordon's Auto Workshop", "Initializing Materials..", 0);
					producer.sendShopMsg(msg);
					try {
						Thread.sleep(3000);   //1000 milliseconds is one second.
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					publisher.publishCustomer(message);
//					msg = new ShopMsg("Gordon's Auto Workshop", "Initializing Materials COMPLETE.", 20);
//					producer.sendShopMsg(msg);
			}
			else if (json.contains("2")) {
				//see if we have the raw materials
				//ResponseEntity<RawMaterialWrapper> resp = restTemplate.exchange(rmmUrl+"/get", HttpMethod.GET, requestEntity, RawMaterialWrapper.class);
				ShopMsg msg = new ShopMsg("Gordon's Auto Workshop", "Checking Materials..", 20);
				producer.sendShopMsg(msg);
				try {
					Thread.sleep(3000);   //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				publisher.publishCustomer(message);
//				msg = new ShopMsg("Gordon's Auto Workshop", "Checking Materials COMPLETE.", 20);
//				producer.sendShopMsg(msg);
			}
			else if (json.contains("3")) {
				//see if we have the raw materials
				//ResponseEntity<RawMaterialWrapper> resp = restTemplate.exchange(rmmUrl+"/get", HttpMethod.GET, requestEntity, RawMaterialWrapper.class);
				ShopMsg msg = new ShopMsg("Gordon's Auto Workshop", "Applying Materials..", 10);
				producer.sendShopMsg(msg);
				try {
					Thread.sleep(3000);   //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				publisher.publishCustomer(message);
//				msg = new ShopMsg("Gordon's Auto Workshop", "Applying Materials COMPLETE.", 10);
//				producer.sendShopMsg(msg);
			}
			else if (json.contains("4")) {
				//see if we have the raw materials
				//ResponseEntity<RawMaterialWrapper> resp = restTemplate.exchange(rmmUrl+"/get", HttpMethod.GET, requestEntity, RawMaterialWrapper.class);
				ShopMsg msg = new ShopMsg("Gordon's Auto Workshop", "Testing Materials..", 10);
				producer.sendShopMsg(msg);
				try {
					Thread.sleep(3000);   //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				publisher.publishCustomer(message);
//				msg = new ShopMsg("Gordon's Auto Workshop", "Testing Materials COMPLETE.", 10);
//				producer.sendShopMsg(msg);
			}
			else if (json.contains("5")) {
				//see if we have the raw materials
				//ResponseEntity<RawMaterialWrapper> resp = restTemplate.exchange(rmmUrl+"/get", HttpMethod.GET, requestEntity, RawMaterialWrapper.class);
				ShopMsg msg = new ShopMsg("Gordon's Auto Workshop", "Materials Applied.", 10);
				producer.sendShopMsg(msg);
				try {
					Thread.sleep(3000);   //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				publisher.publishCustomer(message);
			}
			else {
				try {
					Thread.sleep(2000);   //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				publisher.publishCustomer(message);
			}
			
		} catch (JsonProcessingException e) {
			LOGGER.error("Error: ", e);
		}
        
    }

    

}