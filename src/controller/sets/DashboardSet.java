package controller.sets;

import bus.Bus;
import controller.can.DashboardCAN;
import controller.micro.DashboardController;

public class DashboardSet extends ComponentSet {

    public DashboardSet() {

    }

    public DashboardSet(int id, Bus bus) {
        super(id, bus);
        this.can = new DashboardCAN(id);
        this.micro = new DashboardController(id);
        this.can.attachBus(bus);
        this.micro.attachCan(can);

        can.addObserver(micro);
        bus.addObserver(can);
    }

    @Override
    public void initialize(int id, Bus bus) {
        this.can = new DashboardCAN(id);
        this.micro = new DashboardController(id);
        this.can.attachBus(bus);
        this.micro.attachCan(can);

        can.addObserver(micro);
        bus.addObserver(can);
    }


}
