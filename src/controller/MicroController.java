package controller;

import java.util.Observable;
import java.util.Observer;

public abstract class MicroController implements Observer, Runnable {
	
	private final short id;
	
	public MicroController(short id) {
		this.id = id;
	}


}
