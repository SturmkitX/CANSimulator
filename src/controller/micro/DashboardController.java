package controller.micro;

import codes.RequestCode;
import controller.Controller;
import controller.MicroController;
import frame.DataFrame;
import frame.Frame;
import frame.RemoteFrame;
import utils.UserSession;

import java.util.Observable;


public class DashboardController extends MicroController {


    public DashboardController() {

    }

    public DashboardController(int id) {
        super(id);
    }

    @Override
    public void initializeTransientFields() {

    }

    @Override
    public void run() {

    }

    @Override
    public void update(Observable o, Object arg) {
        Frame frame = (Frame)arg;
        Frame result = null;

        UserSession.appendLog(String.format("[%s] %s ID: %d received frame %s\n\n", ((Controller)o).getBus().getTime(), getClass().getSimpleName(), id, frame.getClass().getName()));

        // send the correct data to the microcontroller, based on request or data
        if(frame instanceof RemoteFrame) {
            switch(frame.getId()) {
                //
            }
        } else if(frame instanceof DataFrame) {
            switch(frame.getId()) {
                //
            }
        }

        // otherwise, the frame is not a remote frame
        if(result != null) {
            ((Controller)o).write(result);
        }
    }

    @MenuAccess
    public void requestEngineTemperature() {
        // this part should be hidden
        if(UserSession.getCurrentBus() == null) {
            return;
        }
        Controller found = cans.get(UserSession.getCurrentBus().getId());

        // send a simple data request
        RemoteFrame testFrame = new RemoteFrame(RequestCode.ENGINE_TEMP_GET);
        testFrame.setDataLength(4);
        testFrame.setCrc(testFrame.computeCrc());
        found.write(testFrame);
    }

}
