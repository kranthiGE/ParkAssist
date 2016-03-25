package sensordata;

public class ReadPirSensor{
	
	private static Thread[] thread = null;

	public static void main(String[] args) {
		System.out.println("Hello, reading sensor data");
		Sensor sensor = null;
		MultipleSensor mSensor = null; //Changed to array
		
		if(args.length > 0){
			String type = args[0];
			
			if(type != null && type.equals("pir")) {
				sensor = new PirSensor();
				sensor.readSensorData();
			}
			else if(type != null && type.equals("multiplesensors")){
				mSensor = new MultipleSensorImpl(args);
				mSensor.registerSensorPairs();
				mSensor.readSensorData();
			}
			else {
				String[] ports = args[1].split(",");
				thread = new Thread[ports.length];				
				System.out.println("total ports: " + ports.length);
				for(int itr=0; itr<ports.length; itr++) { 
					System.out.println("port: " + ports[itr]);
					ReadSensorThreads sensorThread = new ReadSensorThreads(new PirSensorListener(ports[itr]));
					thread[itr] = new Thread(sensorThread, "Thread-" + itr);
					System.out.println("Thread " + thread[itr].getName() + " created");
					thread[itr].start();
					//sensor = new PirSensorListener(ports[itr]);
					//sensor.readSensorData();
				}
				for(int itr=0; itr<=ports.length; itr++)
					try {
						thread[itr].join();
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		}
			
		else{
			System.err.println("Invalid args, usage ReadPirSensor <inputType = pirlistener, pir>");
		}
			
	}
}
 