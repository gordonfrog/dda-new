package com.sh.events;

import org.springframework.context.ApplicationEvent;

import com.sh.messages.ShopMsg;

@SuppressWarnings("serial")
public class ShopEvent extends ApplicationEvent{
	
	private ShopMsg msg;

	public ShopEvent(Object source,ShopMsg msg) {
		super(source);
		this.msg = msg;
	}

	public ShopMsg getMsg() {
		return msg;
	}

}
