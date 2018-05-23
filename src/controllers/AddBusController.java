package controllers;

import bus.BusFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddBusController {

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // fx:id="busIdField"
    private TextField busIdField; // Value injected by FXMLLoader

    @FXML
    private Text status;

    @FXML
    void addBus(ActionEvent event) {
        try {
            int bid = Integer.parseInt(busIdField.getText());

            if(bid < 0) {
                status.setText("Bus ID must be a positive integer!");
                return;
            }

            if(BusFactory.getBus(bid) != null) {
                status.setText("A bus with the specified ID already exists!");
                return;
            }

            BusFactory.createBus(bid);

            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        } catch(NumberFormatException e) {

        }

    }

}
