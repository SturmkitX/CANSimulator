package controller;

import java.util.*;

import bus.Bus;
import frame.Frame;

public abstract class Controller extends Observable implements Observer {

	protected int id;
	protected List<Short> acceptedIds;
	protected Bus bus;
	
	public Controller(int id) {
		this.id = id;
		this.acceptedIds = new ArrayList<>();
		this.bus = null;
		setAcceptedIds();
	}

	protected abstract void setAcceptedIds();

	protected boolean checkError(Frame frame) {
		// TODO
		return true;
	}

	protected boolean checkAcceptedId(Frame frame) {
		return acceptedIds.contains(frame.getId());
	}


	public void write(Frame frame) {
		try {
			bus.queueFrame(frame);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void addAcceptedId(short id) {
		acceptedIds.add(id);
	}

	protected void addAcceptedIds(short... ids) {
		for(short s : ids) {
			addAcceptedId(s);
		}
	}

	public void attachBus(Bus bus) {
		this.bus = bus;
	}

}
