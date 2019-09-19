package com.test;

public enum MessageSeries {
	A(new Producer(), MessageDispatcher.getConsumer1()), B(new Producer(), MessageDispatcher.getConsumer1()), C(new Producer(),MessageDispatcher.getConsumer2()), D(new Producer(),MessageDispatcher.getConsumer2());
	
	private Producer producer;
	private Consumer consumer;
	private static ResultSet resultSet = new ResultSet();
	
	private MessageSeries(Producer producer, Consumer consumer) {
		producer.setSeries(this);
		this.producer = producer;
		this.consumer = consumer;
	}
	
	public static MessageSeries getValueFor(char producerType) {
		switch(producerType){
		case 'A':	return A;
		case 'B':	return B;
		case 'C':	return C;
		case 'D':	return D;
		default:
			throw new IllegalArgumentException("Please select producerType value as 'A', 'B', 'C' or 'D'");
		}
	}

	public Producer getProducer() {
		return producer;
	}
	
	public Consumer getConsumer() {
		return consumer;
	}

	public static ResultSet getResultSet() {
		return resultSet;
	}

	public static void setResultSet(ResultSet resultSet) {
		MessageSeries.resultSet = resultSet;
	}
	
	

}
