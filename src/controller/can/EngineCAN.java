package controller.can;

import controller.Controller;
import frame.Frame;

import java.util.Observable;

public class EngineCAN extends Controller {

    public EngineCAN(int id) {
        super(id);
    }

    @Override
    public void update(Observable o, Object arg) {
        Frame frame = (Frame)arg;

        if(!checkError(frame)) {
            return;
        }


        setChanged();
        notifyObservers(frame);

    }
}
