package starter;

import bus.Bus;
import bus.BusFactory;
import codes.RequestCode;
import controller.Controller;
import controller.MicroController;
import controller.can.EngineCAN;
import controller.micro.EngineController;
import frame.RemoteFrame;

public class MainStarter {

    public static void main(String[] args) {

        // initialize buses
        BusFactory.createBus();

        // initialize CAN controllers
        Controller engineCAN = new EngineCAN(0);

        // initialize microcontrollers
        MicroController engineMicro = new EngineController(0);

        // make attachments
        engineCAN.addObserver(engineMicro);
        Bus bus = BusFactory.getBus(0);
        bus.addObserver(engineCAN);
        engineCAN.attachBus(bus);

        // microcontrollers are concurrently-running threads
        // buses, can controllers are event-based
//        Thread engineThread = new Thread(engineMicro);
//
//        engineThread.start();
        engineMicro.run();

        // send a simple data request
        RemoteFrame testFrame = new RemoteFrame(RequestCode.ENGINE_TEMP_GET);
        testFrame.setDataLength(4);
        engineCAN.write(testFrame);

    }
}
