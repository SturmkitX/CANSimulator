package controllers;

import bus.BusFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBusController {

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // fx:id="busIdField"
    private TextField busIdField; // Value injected by FXMLLoader

    @FXML
    void addBus(ActionEvent event) {
        BusFactory.createBus(Integer.parseInt(busIdField.getText()));

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

}
