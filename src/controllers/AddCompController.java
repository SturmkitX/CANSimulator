package controllers;

import controller.MicroController;
import controller.MicroMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ui.dtos.ComponentDTO;
import utils.UserSession;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCompController implements Initializable {

    @FXML // fx:id="choiceBox"
    private ComboBox<String> choiceBox; // Value injected by FXMLLoader

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // fx:id="compIdField"
    private TextField compIdField; // Value injected by FXMLLoader

    @FXML
    void addComponent(ActionEvent event) {
        try {
            MicroController cs = (MicroController) Class.forName(choiceBox.getSelectionModel().getSelectedItem()).newInstance();
            cs.initialize(Integer.parseInt(compIdField.getText()));
            cs.initializeTransientFields();
            UserSession.getComponents().add(new ComponentDTO(cs));

            new Thread(cs).start();

            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().setAll(MicroMapper.getClasses());
    }
}
