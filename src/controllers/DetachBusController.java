package controllers;

import bus.BusFactory;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import utils.UserSession;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DetachBusController implements Initializable {

    @FXML // fx:id="choiceBox"
    private ComboBox<Integer> choiceBox; // Value injected by FXMLLoader

    @FXML // fx:id="detachButton"
    private Button detachButton; // Value injected by FXMLLoader

    @FXML
    void detachBus(ActionEvent event) {
        if(choiceBox.getSelectionModel().isEmpty()) {
            return;
        }

        Controller found = null;
        int busId = choiceBox.getValue();
        for(Controller c : UserSession.getCurrentMicro().getSource().getCans()) {
            if(c.getBus().getId() == busId) {
                found = c;
                break;
            }
        }

        UserSession.getCurrentMicro().getSource().getCans().remove(found);
        UserSession.getCurrentMicro().canIdsProperty().remove(found.getId());

        Stage stage = (Stage) detachButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Integer> ids = FXCollections.observableList(BusFactory.getBuses().stream().map(b -> b.getId()).collect(Collectors.toList()));
        choiceBox.setItems(ids);

        if(!choiceBox.getItems().isEmpty()) {
            choiceBox.getSelectionModel().select(0);
        }
    }
}
