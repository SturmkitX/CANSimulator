package controller.micro;

import codes.RequestCode;
import controller.Controller;
import controller.MicroController;
import frame.DataFrame;
import frame.Frame;
import frame.RemoteFrame;

import java.nio.ByteBuffer;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class EngineController extends MicroController {

    private Timer timer;
    private int temperature;
    private int rotations;

    public EngineController(int id) {
        super(id);
        timer = new Timer();
        temperature = 0;
        rotations = 0;
    }

    @Override
    public void run() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                temperature = (temperature + 10) % 110;
                rotations = (rotations + 300) % 3300;
            }
        }, 5*1000, 5*1000);
    }

    @Override
    public void update(Observable o, Object arg) {
        Frame frame = (Frame)arg;
        Frame result = null;

        // send the correct data to the microcontroller, based on request or data
        if(frame instanceof RemoteFrame) {
            switch(frame.getId()) {
                case RequestCode.ENGINE_TEMP : result = getTemperature((RemoteFrame)frame); break;
                case RequestCode.ENGINE_ROT: result = getRotations((RemoteFrame)frame); break;
            }
        }

        // otherwise, the frame is not a remote frame
        if(result != null) {
            ((Controller)o).write(result);
        }

    }

    private Frame getTemperature(RemoteFrame src) {
        DataFrame result = new DataFrame(src.getId());
        result.setDataLength(src.getDataLength());
        byte[] data = ByteBuffer.allocate(src.getDataLength()).putInt(temperature).array();
        result.setData(data);
        return result;
    }

    private Frame getRotations(RemoteFrame src) {
        DataFrame result = new DataFrame(src.getId());
        result.setDataLength(src.getDataLength());
        byte[] data = ByteBuffer.allocate(src.getDataLength()).putInt(rotations).array();
        result.setData(data);
        return result;
    }
}
