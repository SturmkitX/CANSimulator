package controller.can;

import bus.Bus;
import codes.RequestCode;
import controller.Controller;
import frame.Frame;

import java.util.Observable;

public class EngineCAN extends Controller {

    public EngineCAN(int id) {
        super(id);
    }

    @Override
    protected void setAcceptedIds() {
        addAcceptedIds(RequestCode.ENGINE_ROT_GET, RequestCode.ENGINE_TEMP_GET);
    }

    @Override
    public void update(Observable o, Object arg) {
        Frame frame = (Frame)arg;

        if(!(checkAcceptedId(frame) && checkError(frame))) {
            return;
        }


        setChanged();
        notifyObservers(frame);

    }
}
