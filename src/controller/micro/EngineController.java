package controller.micro;

import controller.MicroController;
import frame.Frame;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class EngineController extends MicroController {

    private Timer timer;
    private int engineValue;

    public EngineController(short id) {
        super(id);
        timer = new Timer();
        engineValue = 0;
    }

    @Override
    public void run() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                engineValue = (engineValue + 10) % 110;
            }
        }, 5*1000, 5*1000);
    }

    @Override
    public void update(Observable o, Object arg) {
        Frame frame = (Frame)arg;

        // check if it is a data frame sent by this controller
    }
}
