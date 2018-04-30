package controller.micro;

import codes.RequestCode;
import controller.Controller;
import controller.MicroController;
import frame.DataFrame;
import frame.Frame;
import frame.RemoteFrame;

import java.util.Observable;


public class DashboardController extends MicroController {


    public DashboardController(int id) {
        super(id);
    }

    @Override
    public void run() {

    }

    @Override
    public void update(Observable o, Object arg) {
        Frame frame = (Frame)arg;
        Frame result = null;

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
        // send a simple data request
        RemoteFrame testFrame = new RemoteFrame(RequestCode.ENGINE_TEMP_GET);
        testFrame.setDataLength(4);
        can.write(testFrame);
    }
}
