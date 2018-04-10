package bus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;

public class Bus extends Observable implements Runnable {
	
	private static Date date;
	private static DateFormat df;
	
	public Bus() {
		date = new Date();
		df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
	}
	
	public String getTime() {
		date.setTime(System.currentTimeMillis());
		return df.format(date);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
