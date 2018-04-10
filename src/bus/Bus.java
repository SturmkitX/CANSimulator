package bus;

import frame.Frame;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class Bus extends Observable implements Runnable {
	
	private static Date date;
	private static DateFormat df;
	private Frame currentFrame;
	private Queue<Frame> frames;
	
	public Bus() {
		date = new Date();
		df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		currentFrame = null;
		frames = new PriorityBlockingQueue<>();
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
