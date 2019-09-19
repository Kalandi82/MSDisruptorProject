package com.test;

import com.lmax.disruptor.EventFactory;

public class MessageFactory implements EventFactory<Message>
{
	private static MessageFactory instance = new MessageFactory();
    public Message newInstance()
    {
        return new Message();
    }
    
    public static MessageFactory getFactory() {
    	return instance;
    }
    
    private MessageFactory() {}
}