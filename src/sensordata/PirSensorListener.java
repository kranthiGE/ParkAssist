package sensordata;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class PirSensorListener implements Sensor{
	
	public String sensorID = "";
	
	public PirSensorListener(String sensorID){
		 this.sensorID = sensorID;
	}
	
	private Pin getPort() {
		if(sensorID.equalsIgnoreCase("10"))
			return RaspiPin.GPIO_10;
		else if(sensorID.equalsIgnoreCase("11"))
			return RaspiPin.GPIO_11;
		else 
			return RaspiPin.GPIO_07;
	}
	
	private String getSensorID() {
		return sensorID;
	}
	
	public void readSensorData(){ //connection parameter added
	
		// Create gpio controller
		GpioController gpio = GpioFactory.getInstance();
			
		GpioPinDigitalInput sensorInpin = gpio.provisionDigitalInputPin(getPort(), PinPullResistance.PULL_DOWN);
		sensorInpin.addListener(new GpioPinListenerDigital() {
			
			@Override
			public void handleGpioPinDigitalStateChangeEvent(
					GpioPinDigitalStateChangeEvent event) {
				CloudService service = new CloudServiceImpl();//object created for cloud service
				
				if(event.getState().isHigh()){
					System.out.println(getSensorID() + ": HIGH");
					//service.postSensorData("https://karunesh-postgres-service.run.aws-usw02-pr.ice.predix.io/v1/parking/10/low", "");
					service.postSensorData("https://karunesh-postgres-service.run.aws-usw02-pr.ice.predix.io/v1/parking/"+getSensorID(), "");
				}
				
				if(event.getState().isLow()){
					System.out.println(getSensorID() + ": LOW");
				}
			}
		});
		

		try {
			for(;;){
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*sensorInpin.export(PinMode.DIGITAL_OUTPUT);
		do{			
			state = sensorInpin.getState();			
			System.out.println("Name: " + state.getName() + " ,value: "+ state.getValue());
			
			if(state.equals(PinState.HIGH))
				break;			
		}while(true);
		System.out.println("Name: " + state.getName() + " ,value: "+ state.getValue());*/
	}
}
