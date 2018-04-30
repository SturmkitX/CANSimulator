package controller.sets;

import bus.Bus;
import controller.Controller;
import controller.MicroController;

public abstract class ComponentSet {
    protected Controller can;
    protected MicroController micro;

    public ComponentSet(int id, Bus bus) {

    }

    public Controller getCan() {
        return can;
    }

    public MicroController getMicro() {
        return micro;
    }
}
