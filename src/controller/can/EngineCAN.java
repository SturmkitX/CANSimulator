package controller.can;

import controller.Controller;
import frame.Frame;

import java.util.Observable;

public class EngineCAN extends Controller {

    // define the accepted ids and their role
    private static final short cacat = 0;


    public EngineCAN(short id) {
        super(id);
    }

    @Override
    protected boolean checkError() {
        return false;
    }

    @Override
    public Frame read() {
        return null;
    }

    @Override
    public void write(Frame frame) {

    }

    @Override
    public void run() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
