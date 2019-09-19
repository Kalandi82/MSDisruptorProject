package com.test;

import org.apache.log4j.Logger;

public class Consumer {
	

	private static final String PROCESSED = "processed";
	static final Logger logger= Logger.getLogger(Consumer.class);

	public void processMessage(Message message) {
		String rawMessage = message.getMessage();
		message.setMessage(rawMessage.concat(PROCESSED));;
		logger.info("Processed message : "+message);
	}


	
	
	

}
