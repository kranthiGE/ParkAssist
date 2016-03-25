package com.iot.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BulbMain {

	static RemoteService remote = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello Kiran, IOT project");
		do{
			callDeviceService();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(true);
	}
	
	public static void callDeviceService(){
		try {
			remote = RemoteService.getInstance();
			remote.setOperation(new BulbOperation(new Bulb()));
			URL url = new URL("http://krandevice1.elasticbeanstalk.com/rest/device/state");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output = null;
			System.out.println("Output from Server ....");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				if(output != null && output.trim().equals("1")){
					lightBulb();
				}
				else if(output != null && output.trim().equals("0")){
					putBulbOff();
				}
			}
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
	}
	
	public static void lightBulb(){		
		remote.bulbOperation(Bulb.STATE.ON);
		System.out.println("Lightbulb called");
	}
	
	public static void putBulbOff(){
		remote.bulbOperation(Bulb.STATE.OFF);
		System.out.println("putoffbulb called");
	}
}
