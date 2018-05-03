package controllers;

import bus.Bus;
import bus.BusFactory;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.UserSession;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AttachBusController implements Initializable {

    @FXML // fx:id="choiceBox"
    private ComboBox<Integer> choiceBox; // Value injected by FXMLLoader

    @FXML // fx:id="attachButton"
    private Button attachButton; // Value injected by FXMLLoader

    @FXML // fx:id="microId"
    private Text microId; // Value injected by FXMLLoader

    @FXML
    void attachBus(ActionEvent event) {
        if(choiceBox.getSelectionModel().isEmpty()) {
            return;
        }

        Controller c = new Controller(UserSession.generateCanId());   // should ask the user for an ID
        Bus b = BusFactory.getBus(choiceBox.getValue());
        c.attachBus(b);
        UserSession.getCurrentMicro().getSource().attachCan(b.getId(), c);
        UserSession.getCurrentMicro().canIdsProperty().add(c.getId());

        b.addObserver(c);
        c.addObserver(UserSession.getCurrentMicro().getSource());

        Stage stage = (Stage) attachButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Integer> ids = FXCollections.observableList(BusFactory.getBuses().stream().map(b -> b.getId()).collect(Collectors.toList()));
        ObservableList<Integer> attached = FXCollections.observableList(UserSession.getCurrentMicro().getSource().getCans().keySet().stream().collect(Collectors.toList()));

        System.out.println("ids: " + ids);
        System.out.println("remove:" + attached);

        ids.removeAll(attached);

        System.out.println("ids: " + ids);

        choiceBox.setItems(ids);

        if(!choiceBox.getItems().isEmpty()) {
            choiceBox.getSelectionModel().select(0);
        }

        microId.setText(UserSession.getCurrentMicro().getMicroId() + "");
    }
}
