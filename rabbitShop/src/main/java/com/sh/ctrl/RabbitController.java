package com.sh.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sh.messages.CustomerMsg;
import com.sh.messages.ShopMsg;
import com.sh.producers.MsgProducer;

/**
 * 
 * @author shoe011
 *
 */
@RestController
public class RabbitController {
	
	/**
	 * @see MsgProducer
	 */
	@Autowired
	private MsgProducer producer;
	
	/**
	 * GET Method for send a test message for customer
	 */
	@RequestMapping(value="/sendMsgCustomer.rest",method=RequestMethod.GET)
	public void sendMsgCustomer()
	{
		CustomerMsg msg1 = new CustomerMsg("Ford Motor Company", "1.) Initializing Work Order", "BuildCar");
		producer.sendCustomerMsg(msg1);
		try {
			Thread.sleep(3000);   //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
//		msg1 = new CustomerMsg("Ford Motor Company", "Initializing Work Order COMPLETE", "BuildCar");
//		producer.sendCustomerMsg(msg1);
		CustomerMsg msg2 = new CustomerMsg("Ford Motor Company", "2.) Create Work Order", "BuildCar");
		producer.sendCustomerMsg(msg2);
		try {
			Thread.sleep(3000);   //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
//		msg2 = new CustomerMsg("Ford Motor Company", "Create Work Order COMPLETE", "BuildCar");
//		producer.sendCustomerMsg(msg2);
		CustomerMsg msg3 = new CustomerMsg("Ford Motor Company", "3.) Check Inventory for Parts", "BuildCar");
		producer.sendCustomerMsg(msg3);
		try {
			Thread.sleep(3000);   //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
//		msg3 = new CustomerMsg("Ford Motor Company", "Check Inventory for Parts COMPLETE", "BuildCar");
//		producer.sendCustomerMsg(msg3);
		CustomerMsg msg4 = new CustomerMsg("Ford Motor Company", "4.) Start", "BuildCar");
		producer.sendCustomerMsg(msg4);		
		try {
			Thread.sleep(3000);   //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		CustomerMsg msg5 = new CustomerMsg("Ford Motor Company", "5.) Complete", "BuildCar");
		producer.sendCustomerMsg(msg5);
	}
	
	/**
	 * GET Method for send a test message for shop
	 */
//	@RequestMapping(value="/sendMsgShop.rest",method=RequestMethod.GET)
//	public void sendMsgShop()
//	{
//		ShopMsg msg = new ShopMsg("Games Workshop", "Edimburgh", 20);
//		producer.sendShopMsg(msg);
//	}
	
	/**
	 * POST Method for send a test message for customer
	 */
	@RequestMapping(value="/sendMsgCustomer.rest",method=RequestMethod.POST)
	public void sendMsgCustomer(@RequestBody CustomerMsg msg)
	{
		producer.sendCustomerMsg(msg);
	}
	
	/**
	 * POST Method for send a test message for shop
	 */
//	@RequestMapping(value="/sendMsgShop.rest",method=RequestMethod.POST)
//	public void sendMsgShop(@RequestBody ShopMsg msg)
//	{
//		producer.sendShopMsg(msg);
//	}
	
	/**
	 * Information about Rabbit config
	 * @return String - Json Info
	 */
	@RequestMapping(value="/info",method=RequestMethod.GET,produces="application/json")
	public String info()
	{
		ObjectNode root = producer.info();
		
		return root.toString();
	}
	
}
