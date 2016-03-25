package sensordata;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class PirSensor implements Sensor{

	public void readSensorData(){ //connection parameter added
		// Create gpio controller
		GpioController gpio = GpioFactory.getInstance();			
		GpioPinDigitalInput sensorInpin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, PinPullResistance.PULL_DOWN);
		PinState state = null;
		
		do{			
			state = sensorInpin.getState();			
			System.out.println("Name: " + state.getName() + " ,value: "+ state.getValue());
			
			if(state.equals(PinState.HIGH))
				break;			
		}while(true);
		
		System.out.println("Name: " + state.getName() + " ,value: "+ state.getValue());
	}
}
