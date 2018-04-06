package controller;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class MicroController implements Observer, Runnable {
	
	private final short id;
	
	public MicroController(short id) {
		this.id = id;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
