package com.sh.events.publisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.sh.events.CustomerEvent;
import com.sh.events.ShopEvent;
import com.sh.messages.CustomerMsg;
import com.sh.messages.ShopMsg;

@Component
public class EventsPublisher implements ApplicationEventPublisherAware{
	
	protected ApplicationEventPublisher appPublisher;

	@Override
	public void setApplicationEventPublisher(final ApplicationEventPublisher appPublisher) {
		
		this.appPublisher = appPublisher;
	}
	
	public void publishCustomer(CustomerMsg msg)
	{
		CustomerEvent evt = new CustomerEvent(this, msg);
		appPublisher.publishEvent(evt);
	}
	
	public void publishShop(ShopMsg msg) {
		ShopEvent evt = new ShopEvent(this, msg);
		appPublisher.publishEvent(evt);
	}

}
