package bus;

import frame.Frame;
import utils.UserSession;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Bus extends Observable implements Serializable {

	private int id;
	private static Date date;
	private static DateFormat df;
	private BlockingQueue<Frame> frames;
	
	public Bus() {
		this.id = 0;
		date = new Date();
		df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
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

	public void queueFrame(Frame frame) throws InterruptedException {
		df.format(new Date());
		df.format(date);
		UserSession.appendLog(String.format("[%s]: Bus %d received %s\n\n", df.format(date), id, frame.getClass()));
		frames.add(frame);
		Frame imp = frames.take();
		setChanged();
		notifyObservers(imp);
	}

	public boolean equals(Object o) {
		Bus b = (Bus)o;
		return (this.id == b.getId());
	}

	public void initializeTransient() {
		// static fields are implicitly transient
		date = new Date();
		df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
	}

}
