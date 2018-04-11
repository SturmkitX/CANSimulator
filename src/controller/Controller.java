package controller;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import bus.Bus;
import bus.BusInstanceHandler;
import frame.Frame;

public abstract class Controller extends Observable implements Observer {

	protected short id;
	protected List<Short> acceptedIds;
	protected List<Bus> buses;
	
	public Controller(short id) {
		this.id = id;
		this.acceptedIds = new ArrayList<>();
		this.buses = new ArrayList<>();
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


	public void write(int busId, Frame frame) {
		try {
			buses.get(busId).queueFrame(frame);
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

	public void attachBus(int index) {
		Bus found = BusInstanceHandler.getBus(index);
		if(found != null) {
			buses.add(found);
		}
		found.addObserver(this);
	}
}
