package com.test;

import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
public class ProducerManager {
	

	static final Logger logger= Logger.getLogger(ProducerManager.class);
	private Disruptor<Message> disruptor=new Disruptor<>(MessageFactory.getFactory(), 1024, Executors.newSingleThreadExecutor());
	private RingBuffer<Message> ringBuffer;
	
	
	public void createMessageAndPutOnQueue(char producerType) {
		MessageSeries messageSeries = MessageSeries.getValueFor(producerType);
		Message message = createMessageForProducer(messageSeries);
		
		long next = ringBuffer.next();
		Message message2 = ringBuffer.get(next);
		message2.setMessage(message.getMessage());
		message2.setMessageSeries(messageSeries);
		message2.setPriority(message.getPriority());
		logger.info("Message Created : " + message +" :: Pushing to Queue..");
		ringBuffer.publish(next);

	}
	
	public void startDisruptor() {
		disruptor.handleEventsWith(new MessageDispatcher());
		ringBuffer = disruptor.start();
	}
	

	private Message createMessageForProducer(MessageSeries messageSeries){
		Producer producer = messageSeries.getProducer();
		Message message =  producer.createNextMessage(messageSeries);
		return message;
	}
	

}
