package com.iot.samples;

public class RemoteService {

	Operation operation;
	
	private static RemoteService service = new RemoteService();
	
	private RemoteService(){}
	
	public static RemoteService getInstance(){
		return service;
	}
	
	public void setOperation(Operation operation){
		this.operation = operation;
	}
	
	public void bulbOperation(Bulb.STATE state){
		operation.execute(state);
	}
}
