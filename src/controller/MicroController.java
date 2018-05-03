package controller;

import java.io.Serializable;
import java.util.*;

public abstract class MicroController implements Observer, Runnable, Serializable {
	
	protected int id;
	protected Map<Integer, Controller> cans;

	public MicroController() {

    }
	
	public MicroController(int id) {
		this.id = id;
		this.cans = new HashMap<>();
	}

	public void initialize(int id) {
	    this.id = id;
	    this.cans = new HashMap<>();
    }

	public Map<Integer, Controller> getCans() {
		return cans;
	}

	public void attachCan(int busId, Controller can) {
		this.cans.put(busId, can);
	}

	public int getId() {
		return id;
	}

	public abstract void initializeTransientFields();
}
