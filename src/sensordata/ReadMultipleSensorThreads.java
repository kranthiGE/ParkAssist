package sensordata;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;

public class ReadMultipleSensorThreads implements Runnable {
	
	private String motionPin = null;
	private String iRPin = null;
	
	public ReadMultipleSensorThreads(String motionPin, String iRPin){
		this.motionPin = motionPin;
		this.iRPin = iRPin;
	}
	
	@Override
	public void run(){
		readData();
	}
	
	public void readData(){ 
		// Create gpio controller
		GpioController gpio = GpioFactory.getInstance();					
		
		GpioPinDigitalInput motionInpin = gpio.provisionDigitalInputPin(WiringPiPins.getPort(motionPin), PinPullResistance.PULL_DOWN);
		
		GpioPinDigitalInput iRInpin = gpio.provisionDigitalInputPin(WiringPiPins.getPort(iRPin), PinPullResistance.PULL_DOWN);
		
		PinState motionState = null, iRState = null;
		
		CloudServiceImpl service = new CloudServiceImpl();
		
		String baseUri = "https://karunesh-postgres-service.run.aws-usw02-pr.ice.predix.io/v1/parking/";
		
		do{			
			motionState = motionInpin.getState();
			iRState = iRInpin.getState();
			
			if(motionState.equals(PinState.HIGH)){
					if(iRState.equals(PinState.HIGH)){
						//send Occupied state
						System.out.println("Parking slot Occupied in " + motionPin + " or " + iRPin);
						String uri = baseUri + motionPin + "/occupied";
						service.postSensorData(uri, "");
					} else {
						//send Vacant state
						System.out.println("Parking slot Vacant" + motionPin + " or " + iRPin);
						String uri = baseUri + motionPin + "/vacant";
						service.postSensorData(uri, "");
					}	
			}				
		}while(true);
	}
}
