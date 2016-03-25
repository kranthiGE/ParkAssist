package sensordata;

public class ReadSensorThreads implements Runnable {

	Sensor sensor = null;
	
	public ReadSensorThreads(Sensor sensor){
		this.sensor = sensor;
	}
	
	@Override
	public void run(){
		sensor.readSensorData();
	}
}
