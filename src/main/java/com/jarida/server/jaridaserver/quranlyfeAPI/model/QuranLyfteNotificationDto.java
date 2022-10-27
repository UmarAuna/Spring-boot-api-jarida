package com.jarida.server.jaridaserver.quranlyfeAPI.model;


public class QuranLyfteNotificationDto{

	private Data data;

	private String to;

	private String priority;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setTo(String to){
		this.to = to;
	}

	public String getTo(){
		return to;
	}

	public void setPriority(String priority){
		this.priority = priority;
	}

	public String getPriority(){
		return priority;
	}

	@Override
 	public String toString(){
		return 
			"QuranLyfteNotificationDto{" + 
			"data = '" + data + '\'' + 
			",to = '" + to + '\'' + 
			",priority = '" + priority + '\'' + 
			"}";
		}
}