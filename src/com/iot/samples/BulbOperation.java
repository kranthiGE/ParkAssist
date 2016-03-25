package com.iot.samples;

public class BulbOperation implements Operation {

	Bulb bulb = null;
	
	public BulbOperation(Bulb bulb){
		this.bulb = bulb;
	}
	
	public void execute(Bulb.STATE state){
		if(state.equals(Bulb.STATE.ON))
			bulb.On();
		else
			bulb.Off();
	}
}
