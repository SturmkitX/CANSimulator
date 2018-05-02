package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public abstract class MicroController implements Observer, Runnable, Serializable {
	
	protected int id;
	protected List<Controller> cans;

	public MicroController() {

    }
	
	public MicroController(int id) {
		this.id = id;
		this.cans = new ArrayList<>();
	}

	public void initialize(int id) {
	    this.id = id;
	    this.cans = new ArrayList<>();
    }

	public List<Controller> getCans() {
		return cans;
	}

	public void attachCan(Controller can) {
		this.cans.add(can);
	}

	public int getId() {
		return id;
	}

	public abstract void initializeTransientFields();
}
