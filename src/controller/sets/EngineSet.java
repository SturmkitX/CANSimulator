package controller.sets;

import bus.Bus;
import controller.can.EngineCAN;
import controller.micro.EngineController;

public class EngineSet extends ComponentSet {

    public EngineSet() {

    }

    public EngineSet(int id, Bus bus) {
        super(id, bus);
        this.can = new EngineCAN(id);
        this.micro = new EngineController(id);
        this.can.attachBus(bus);
        this.micro.attachCan(can);

        can.addObserver(micro);
        bus.addObserver(can);
    }

    @Override
    public void initialize(int id, Bus bus) {
        this.can = new EngineCAN(id);
        this.micro = new EngineController(id);
        this.can.attachBus(bus);
        this.micro.attachCan(can);

        can.addObserver(micro);
        bus.addObserver(can);
    }


}
