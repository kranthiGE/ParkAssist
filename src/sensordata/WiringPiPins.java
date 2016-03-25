package sensordata;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class WiringPiPins {

	public static Pin getPort(String pin) {
		if(pin.equalsIgnoreCase("2"))
			return RaspiPin.GPIO_02;
		else if(pin.equalsIgnoreCase("3"))
			return RaspiPin.GPIO_03;
		else if(pin.equalsIgnoreCase("12"))
			return RaspiPin.GPIO_12;
		else if(pin.equalsIgnoreCase("13"))
			return RaspiPin.GPIO_13;
		else if(pin.equalsIgnoreCase("14"))
			return RaspiPin.GPIO_14;
		else if(pin.equalsIgnoreCase("1"))
			return RaspiPin.GPIO_01;
		else if(pin.equalsIgnoreCase("4"))
			return RaspiPin.GPIO_04;
		else if(pin.equalsIgnoreCase("5"))
			return RaspiPin.GPIO_05;
		else if(pin.equalsIgnoreCase("6"))
			return RaspiPin.GPIO_06;
		else if(pin.equalsIgnoreCase("10"))
			return RaspiPin.GPIO_10;
		else if(pin.equalsIgnoreCase("11"))
			return RaspiPin.GPIO_11;
		else if(pin.equalsIgnoreCase("21"))
			return RaspiPin.GPIO_21;
		else if(pin.equalsIgnoreCase("22"))
			return RaspiPin.GPIO_22;
		else if(pin.equalsIgnoreCase("23"))
			return RaspiPin.GPIO_23;
		else if(pin.equalsIgnoreCase("24"))
			return RaspiPin.GPIO_24;
		else if(pin.equalsIgnoreCase("25"))
			return RaspiPin.GPIO_25;
		else if(pin.equalsIgnoreCase("26"))
			return RaspiPin.GPIO_26;
		else if(pin.equalsIgnoreCase("27"))
			return RaspiPin.GPIO_27;
		else if(pin.equalsIgnoreCase("28"))
			return RaspiPin.GPIO_28;
		else if(pin.equalsIgnoreCase("29"))
			return RaspiPin.GPIO_29;
		else
			return RaspiPin.GPIO_07;
	}
}
