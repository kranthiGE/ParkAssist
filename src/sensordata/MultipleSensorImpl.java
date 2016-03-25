package sensordata;

public class MultipleSensorImpl extends MultipleSensor {

	String[] pinPairs = null;
	String[] multiplePinPairs = null;
	
	private static Thread[] thread = null;
	
	public MultipleSensorImpl(String[] multiplePinPairs){
		this.multiplePinPairs = multiplePinPairs;		
	}
	
	public void readSensorData(){
		thread = new Thread[pinPairs.length];
		for(int itr=0; itr<pinPairs.length; itr++) {
			String[] pins = pinPairs[itr].split(",");
			ReadMultipleSensorThreads sensorThread = new ReadMultipleSensorThreads(pins[0], pins[1]);
			thread[itr] = new Thread(sensorThread, "Thread-" + itr);
			System.out.println("Thread " + thread[itr].getName() + " created");
			thread[itr].start();
		}
		for(int itr=0; itr<=pinPairs.length; itr++)
			try {
				thread[itr].join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void registerSensorPairs() {
		if(multiplePinPairs.length > 0){
			this.pinPairs = multiplePinPairs[1].split("-");
		}
	}
}
