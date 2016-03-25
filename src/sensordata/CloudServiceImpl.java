package sensordata;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CloudServiceImpl implements CloudService {
	
	public CloudServiceImpl(){
		
	}
	
	public static void main(String[] args){
		CloudServiceImpl service = new CloudServiceImpl();
		service.postSensorData("https://karunesh-postgres-service.run.aws-usw02-pr.ice.predix.io/v1/parking/24/vacant", "");
	}
	
	public void postSensorData(String urlPath, String params){
		URL url = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		String line;
		BufferedReader rd = null;
		DataOutputStream wr = null;
		try{
			url = new URL(urlPath);
			
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", 
			           "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", "" + 
		               Integer.toString(params.getBytes().length));

			conn.setDoInput(true);
		    conn.setDoOutput(true);
			
			wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(params);
			wr.flush();

			System.out.println(conn.getResponseCode());
			
			//Get Response	
		    /*is = conn.getInputStream();
		    rd = new BufferedReader(new InputStreamReader(is));
		    
		    StringBuffer response = new StringBuffer(); 
		    while((line = rd.readLine()) != null) {
		    	response.append(line);
		    	response.append('\r');
		    }
		    
		    System.out.println(response.toString());*/

		} catch (MalformedURLException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		}
		finally{
			try {
				if(rd != null)
					rd.close();
				if(conn != null)
					conn.disconnect();
				if(wr != null)
					wr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
