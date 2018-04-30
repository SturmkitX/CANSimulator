package controller.can;

import controller.Controller;
import frame.Frame;
import utils.UserSession;

import java.util.Observable;

public class EngineCAN extends Controller {

    public EngineCAN(int id) {
        super(id);
    }

    @Override
    public void update(Observable o, Object arg) {
        Frame frame = (Frame)arg;
        UserSession.appendLog(String.format("[%s]: Engine CAN Controller received frame\n\n", bus.getTime()));

        if(!checkError(frame)) {
            return;
        }


        setChanged();
        notifyObservers(frame);

    }
}
