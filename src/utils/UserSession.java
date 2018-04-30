package utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.dtos.ComponentDTO;

import java.util.ArrayList;

public class UserSession {

    private static StringProperty log = new SimpleStringProperty("");
    private static ObservableList<ComponentDTO> components = FXCollections.observableList(new ArrayList<>());

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
}
