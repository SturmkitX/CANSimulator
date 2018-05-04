package controllers;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.dtos.CanViewDTO;
import ui.dtos.ComponentDTO;
import utils.UserSession;

import java.net.URL;
import java.util.ResourceBundle;

public class CanViewController implements Initializable {

    @FXML // fx:id="table"
    private TableView<CanViewDTO> table; // Value injected by FXMLLoader

    @FXML // fx:id="microIdCol"
    private TableColumn<CanViewDTO, Integer> microIdCol; // Value injected by FXMLLoader

    @FXML // fx:id="canIdCol"
    private TableColumn<CanViewDTO, Integer> canIdCol; // Value injected by FXMLLoader

    @FXML // fx:id="busIdCol"
    private TableColumn<CanViewDTO, Integer> busIdCol; // Value injected by FXMLLoader

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<CanViewDTO> views = FXCollections.observableArrayList();

        for(ComponentDTO cd : UserSession.getComponents()) {
            for(Controller co : cd.getSource().getCans().values()) {
                views.add(new CanViewDTO(cd.getMicroId(), co.getId(), co.getBus().getId()));
            }
        }
        table.setItems(views);

        microIdCol.setCellValueFactory(new PropertyValueFactory<CanViewDTO, Integer>("microId"));
        canIdCol.setCellValueFactory(new PropertyValueFactory<CanViewDTO, Integer>("canId"));
        busIdCol.setCellValueFactory(new PropertyValueFactory<CanViewDTO, Integer>("busId"));
    }
}
