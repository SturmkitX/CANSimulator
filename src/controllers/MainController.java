package controllers;

import bus.BusFactory;
import controller.sets.ComponentSet;
import controller.sets.DashboardSet;
import controller.sets.EngineSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import ui.dtos.ComponentDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private ObservableList<ComponentDTO> componentList;

    @FXML // fx:id="drawnComponents"
    private ListView<ComponentDTO> drawnComponents; // Value injected by FXMLLoader

    @FXML // fx:id="componentActions"
    private ListView<Button> componentActions; // Value injected by FXMLLoader

    @FXML // fx:id="componentName"
    private Text componentName; // Value injected by FXMLLoader

    @FXML // fx:id="componentCanId"
    private Text componentCanId; // Value injected by FXMLLoader

    @FXML // fx:id="componentMicroId"
    private Text componentMicroId; // Value injected by FXMLLoader

    @FXML // fx:id="logText"
    private Text logText; // Value injected by FXMLLoader

    @FXML // fx:id="loadMenu"
    private MenuItem loadMenu; // Value injected by FXMLLoader

    @FXML // fx:id="saveMenu"
    private MenuItem saveMenu; // Value injected by FXMLLoader

    @FXML // fx:id="closeMenu"
    private MenuItem closeMenu; // Value injected by FXMLLoader

    @FXML // fx:id="createComponent"
    private MenuItem createComponent; // Value injected by FXMLLoader

    @FXML // fx:id="deleteComponent"
    private MenuItem deleteComponent; // Value injected by FXMLLoader

    @FXML // fx:id="aboutMenu"
    private MenuItem aboutMenu; // Value injected by FXMLLoader

    @FXML
    void closeAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void insertComponent(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        componentList = FXCollections.observableList(new ArrayList<>());
        drawnComponents.setItems(componentList);

        BusFactory.createBus();
        ComponentSet cs = new EngineSet(0, BusFactory.getBus(0));
        componentList.add(new ComponentDTO(cs));

        ComponentSet cs2 = new DashboardSet(1, BusFactory.getBus(0));
        componentList.add(new ComponentDTO(cs2));

        drawnComponents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ComponentDTO>() {
            @Override
            public void changed(ObservableValue<? extends ComponentDTO> observable, ComponentDTO oldValue, ComponentDTO newValue) {
                System.out.println(newValue);
                componentName.setText(newValue.getSource().getClass().getSimpleName());
                componentCanId.setText("" + newValue.getCanId());
                componentMicroId.setText("" + newValue.getMicroId());
                componentActions.setItems(newValue.buttonsProperty());
            }
        });

    }
}
