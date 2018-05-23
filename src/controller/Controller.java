package controller;

import java.io.Serializable;
import java.util.*;

import bus.Bus;
import frame.ErrorFrame;
import frame.Frame;
import utils.UserSession;

public class Controller extends Observable implements Observer, Serializable {

	private int id;
	private Bus bus;
	private String name;

	
	public Controller(int id) {
		this.id = id;
		this.bus = null;
		this.name = "";
	}

	public Controller(int id, String name) {
		this.id = id;
		this.bus = null;
		this.name = name;
	}

	private boolean checkError(Frame frame) {
		return (frame.getCrc() == frame.computeCrc());
	}

	public void write(Frame frame) {
		try {
			bus.queueFrame(frame);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public int getId() {
		return id;
	}

	public Bus getBus() {
		return bus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void attachBus(Bus bus) {
		this.bus = bus;
	}

	@Override
	public void update(Observable o, Object arg) {
		Frame frame = (Frame)arg;
		UserSession.appendLog(String.format("[%s]: %s CAN Controller received frame\n\n", bus.getTime(), name));

		if(!checkError(frame)) {
			// send error frame
			UserSession.appendLog(String.format("[%s]: %s CAN Controller detected error\n\n", bus.getTime(), name));
			ErrorFrame errFrame = new ErrorFrame();
			write(errFrame);
			return;
		}


		setChanged();
		notifyObservers(frame);
	}

	public boolean equals(Object o) {
		Controller c = (Controller)o;
		return (id == c.getId());
	}
}
