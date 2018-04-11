package controller;

import java.util.Observable;
import java.util.Observer;

public abstract class MicroController implements Observer, Runnable {
	
	private final int id;
	
	public MicroController(int id) {
		this.id = id;
	}


}
