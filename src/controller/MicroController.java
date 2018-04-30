package controller;

import java.util.Observer;

public abstract class MicroController implements Observer, Runnable {
	
	protected final int id;
	protected Controller can;
	
	public MicroController(int id) {
		this.id = id;
	}

	public Controller getCan() {
		return can;
	}

	public void setCan(Controller can) {
		this.can = can;
	}

	public int getId() {
		return id;
	}
}
