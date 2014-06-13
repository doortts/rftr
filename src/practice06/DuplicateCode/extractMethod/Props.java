package practice06.DuplicateCode.extractMethod;

import java.util.*;

public class Props 
{
	public void getTimes(Properties props) throws Exception {
		String valueString = null;
		int value = 0;
		
		valueString = props.getProperty("interval");
		if (valueString == null) {
			throw new Exception("monitor interval");
		}
		value = Integer.parseInt(valueString);
		
		if (value <= 0) {
			throw new Exception("monitor interval > 0");
		}
		int checkInterval = value;
		
		valueString = props.getProperty("duration");
		if (valueString == null) {
			throw new Exception("duration");
		}
		value = Integer.parseInt(valueString);
		if (value <= 0) {
			throw new Exception("duration > 0");
		}
		if ((value % checkInterval) != 0) {
			throw new Exception("duration % checkInterval");
		}
		int monitorTime = value;
		
		valueString = props.getProperty("departure");
		if (valueString == null) {
			throw new Exception("departure offset");
		}
		value = Integer.parseInt(valueString);
		if (value <= 0) {
			throw new Exception("departure > 0");
		}
		if ((value % checkInterval) != 0) {
			throw new Exception("departure % checkInterval?");
		}
		int departureOffset = value;
	}
}
