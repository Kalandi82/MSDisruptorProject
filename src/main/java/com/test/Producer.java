package com.test;

public class Producer {
	
	private int sequence;
	private MessageSeries series;
	
	
	public Producer(){
		this.sequence = 0;
	}
	
	public Message createNextMessage(MessageSeries series){
		String message = series.toString() + ++sequence;
		return new Message(message, sequence, series);
	}
	
	public int getCurrentMessageSequence(){
		return sequence ;
	}
	
	public MessageSeries getSeries() {
		return series;
	}

	public void setSeries(MessageSeries series) {
		this.series = series;
	}


}
