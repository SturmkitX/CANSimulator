package controller;

import java.util.Observer;

public abstract class MicroController implements Observer, Runnable {
	
	protected final int id;
	
	public MicroController(int id) {
		this.id = id;
	}


}
