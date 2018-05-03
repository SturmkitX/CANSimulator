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
import ui.dtos.ComponentDTO;
import utils.UserSession;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DeleteBusController implements Initializable {

    @FXML // fx:id="choiceBox"
    private ComboBox<Integer> choiceBox; // Value injected by FXMLLoader

    @FXML // fx:id="addButton"
    private Button delButton; // Value injected by FXMLLoader

    @FXML
    void deleteBus(ActionEvent event) {
        if(choiceBox.getSelectionModel().isEmpty()) {
            return;
        }

        // delete all CAN controllers connected to the bus
        int busId = choiceBox.getValue();
        for(ComponentDTO cd : UserSession.getComponents()) {
            if(cd.getSource().getCans().containsKey(busId)) {
                Controller c = cd.getSource().getCans().get(busId);
                cd.getSource().getCans().remove(busId);
                cd.canIdsProperty().remove(c.getId());
                break;
            }
        }

        BusFactory.deleteBus(busId);

        Stage stage = (Stage) delButton.getScene().getWindow();
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
