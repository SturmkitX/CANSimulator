package bus;

import frame.Frame;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Bus extends Observable {

	private int id;
	private static Date date;
	private static DateFormat df;
	private Frame currentFrame;
	private BlockingQueue<Frame> frames;
	
	public Bus() {
		this.id = 0;
		date = new Date();
		df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		currentFrame = null;
		frames = new PriorityBlockingQueue<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		date.setTime(System.currentTimeMillis());
		return df.format(date);
	}

	public Frame getCurrentFrame() {
		return frames.peek();
	}

	public void queueFrame(Frame frame) throws InterruptedException {
		System.out.println(String.format("Bus %d received frame at %s\n", id, date));
		frames.add(frame);
		Frame imp = frames.take();
		setChanged();
		notifyObservers(imp);
	}

}
