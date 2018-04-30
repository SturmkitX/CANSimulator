package controller.sets;

import bus.Bus;
import controller.Controller;
import controller.MicroController;

import java.io.Serializable;

public abstract class ComponentSet implements Serializable {
    protected Controller can;
    protected MicroController micro;

    public ComponentSet() {

    }

    public ComponentSet(int id, Bus bus) {

    }

    public abstract void initialize(int id, Bus bus);

    public Controller getCan() {
        return can;
    }

    public MicroController getMicro() {
        return micro;
    }
}
