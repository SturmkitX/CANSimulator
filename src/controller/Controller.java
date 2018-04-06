package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import frame.Frame;

@SuppressWarnings("deprecation")
public abstract class Controller extends Observable implements Observer, Runnable {

	protected short id;
	protected List<Frame> frames;
	
	public Controller(short id) {
		this.id = id;
		this.frames = new ArrayList<Frame>();
	}
	
	protected abstract boolean checkError();
	public abstract Frame read();
	public abstract void write(Frame frame);
}
