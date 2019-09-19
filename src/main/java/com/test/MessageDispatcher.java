package com.test;

import org.apache.log4j.Logger;

import com.lmax.disruptor.EventHandler;

public class MessageDispatcher implements EventHandler<Message>{
	static final Logger logger= Logger.getLogger(MessageDispatcher.class);
	
	private static Consumer consumer1 = new Consumer();
	private static Consumer consumer2 = new Consumer();
	
	public static Consumer getConsumer1() {
		return consumer1;
	}


	public static Consumer getConsumer2() {
		return consumer2;
	}
	
	@Deprecated
	public void dispatch(Message message) {
		Consumer consumer = message.getMessageSeries().getConsumer();
		logger.info("Dispatched message : "+message+" to :: " + (consumer == consumer1 ? "Consumer-1" : "Consumer-2"));
		consumer.processMessage(message);
		pushToResultset(message);
		
	}
	
	public void pushToResultset(Message message) {
		ResultSet rs = MessageSeries.getResultSet();
		rs.add(message);
	}
	
	@Override
	public void onEvent(Message message, long arg1, boolean arg2) throws Exception {
		Consumer consumer = message.getMessageSeries().getConsumer();
		consumer.processMessage(message);
		logger.info("Dispatched message : "+message+" to :: " + (consumer == consumer1 ? "Consumer-1" : "Consumer-2"));
		pushToResultset(message);
		
	}

}
