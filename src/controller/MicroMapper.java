package controller;

import controller.micro.DashboardController;
import controller.micro.EngineController;

public class MicroMapper {

    private MicroMapper() {

    }

    private static final String[] classes = new String[] {
            DashboardController.class.getName(),
            EngineController.class.getName()
    };

    public static String[] getClasses() {
        return classes;
    }
}
