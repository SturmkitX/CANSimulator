package controller;

import java.io.Serializable;
import java.util.*;

import bus.Bus;
import frame.Frame;

public abstract class Controller extends Observable implements Observer, Serializable {

	protected int id;
	protected Bus bus;
	
	public Controller(int id) {
		this.id = id;
		this.bus = null;
	}

	protected boolean checkError(Frame frame) {
		// TODO
		return true;
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

	public void attachBus(Bus bus) {
		this.bus = bus;
	}

}
