package com.iot.samples;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Bulb {
	
	public enum STATE {
		ON,
		OFF
	}
	
	private Integer state; 

	private static GpioController gpio = null;
	
	private static GpioPinDigitalOutput led1 = null;
	
	public Integer getState(){
		return state;
	}
	
	public void On(){
		state = 1;
		if(gpio == null){
			// Create gpio controller
			gpio = GpioFactory.getInstance();
		}
		if(led1 == null){
        	led1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
            led1.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        }
        //	Continuously blink the led every 1/2 second for 15 seconds
        if(led1 != null)
        	led1.blink(500, 15000);
		System.out.println("LED will now blink");
	}
	
	public void Off(){
		System.out.println(" .. shutting down now ...");
		if(led1 != null && gpio != null){
			led1.low();
			gpio.shutdown();
		}
		state = 0;
	}

}