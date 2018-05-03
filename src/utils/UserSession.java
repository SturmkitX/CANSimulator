package utils;

import bus.Bus;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.dtos.ComponentDTO;

import java.util.ArrayList;

public class UserSession {

    private static StringProperty log = new SimpleStringProperty("");
    private static ObservableList<ComponentDTO> components = FXCollections.observableList(new ArrayList<>());
    private static ComponentDTO currentMicro = null;
    private static Bus currentBus = null;

    private UserSession() {

    }

    public static String getLog() {
        return log.get();
    }

    public static StringProperty logProperty() {
        return log;
    }

    public static void appendLog(String text) {
        log.setValue(log.get() + text);
    }

    public static ObservableList<ComponentDTO> getComponents() {
        return components;
    }

    public static ComponentDTO getCurrentMicro() {
        return currentMicro;
    }

    public static void setCurrentMicro(ComponentDTO currentMicro) {
        UserSession.currentMicro = currentMicro;
    }

    public static Bus getCurrentBus() {
        return currentBus;
    }

    public static void setCurrentBus(Bus currentBus) {
        UserSession.currentBus = currentBus;
    }
}
