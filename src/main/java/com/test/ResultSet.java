package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ResultSet {
	private Map<MessageSeries, PriorityQueue<Message>> mapResults = new HashMap<>();
	
	public void add(Message message) {
		PriorityQueue<Message> list = mapResults.get(message.getMessageSeries());
		if(list == null) {
			list = new PriorityQueue<>();
			mapResults.put(message.getMessageSeries(), list);
		}
		list.add(message);
	}
	
	public List<Message> getAList(){
		return getListForSeries(MessageSeries.A);
	}
	
	public List<Message> getBList(){
		return getListForSeries(MessageSeries.B);
	}
	
	public List<Message> getCList(){
		return getListForSeries(MessageSeries.C);
	}
	
	public List<Message> getDList(){
		return getListForSeries(MessageSeries.D);
	}
	
	public List<Message> getAllList(){
		List<Message> ret = new ArrayList<>();
		ret.addAll(getAList());
		ret.addAll(getBList());
		ret.addAll(getCList());
		ret.addAll(getDList());
		return ret;
	}

	private List<Message> getListForSeries(MessageSeries messageSeries) {
		List<Message> ret = new ArrayList<>();
		PriorityQueue<Message> list = mapResults.get(messageSeries);
		while(list != null && !list.isEmpty()) {
			ret.add(list.poll());
		}
		return ret;
	}
}
