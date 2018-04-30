package controller;

import java.io.Serializable;
import java.util.Observer;

public abstract class MicroController implements Observer, Runnable, Serializable {
	
	protected final int id;
	protected Controller can;
	
	public MicroController(int id) {
		this.id = id;
	}

	public Controller getCan() {
		return can;
	}

	public void attachCan(Controller can) {
		this.can = can;
	}

	public int getId() {
		return id;
	}

	public abstract void initializeTransientFields();
}
