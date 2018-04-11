package controller.can;

import bus.Bus;
import codes.RequestCode;
import controller.Controller;
import frame.Frame;

import java.util.Observable;

public class EngineCAN extends Controller {

    public EngineCAN(short id) {
        super(id);
    }

    @Override
    protected void setAcceptedIds() {
        addAcceptedIds(RequestCode.ENGINE_ROT, RequestCode.ENGINE_TEMP);
    }

    @Override
    public void update(Observable o, Object arg) {
        Bus bus = (Bus)o;
        Frame frame = (Frame)arg;

        if(checkAcceptedId(frame) && checkError(frame)) {
            setChanged();
            notifyObservers(frame);
        }


    }
}
