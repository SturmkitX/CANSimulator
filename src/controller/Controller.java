package controller;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import frame.Frame;

@SuppressWarnings("deprecation")
public abstract class Controller extends Observable implements Observer, Runnable {

	protected short id;
	protected Queue<Frame> frames;
	protected List<Short> acceptedIds;
	
	public Controller(short id) {
		this.id = id;
		this.frames = new LinkedBlockingQueue<>();
		this.acceptedIds = new ArrayList<>();
	}
	
	protected abstract boolean checkError();
	public abstract Frame read();
	public abstract void write(Frame frame);

	public void addAcceptedId(short id) {
		acceptedIds.add(id);
	}
}
