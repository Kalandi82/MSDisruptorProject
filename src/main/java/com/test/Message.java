package com.test;

public class Message implements Comparable<Message>{
	private String message;
	private int priority;
	private MessageSeries messageSeries;
	
	public Message() {}
	
	
	public Message(String message, int priority, MessageSeries messageSeries){
		this.message = message;
		this.priority = priority;
		this.messageSeries=messageSeries;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(Message o) {
		return this.priority - o.priority;
	}

	public MessageSeries getMessageSeries() {
		return messageSeries;
	}

	public void setMessageSeries(MessageSeries messageSeries) {
		this.messageSeries = messageSeries;
	}

	@Override
	public String toString() {
		return "Message [message=" + message + "]";
	}
	
	
}
