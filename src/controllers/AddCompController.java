package controllers;

import controller.MicroController;
import controller.MicroMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
    private Text status;

    @FXML
    void addComponent(ActionEvent event) {
        try {
            int csId = Integer.parseInt(compIdField.getText());

            MicroController cs = (MicroController) Class.forName(choiceBox.getSelectionModel().getSelectedItem()).newInstance();
            cs.initialize(csId);
            cs.initializeTransientFields();
            UserSession.getComponents().add(new ComponentDTO(cs));

            new Thread(cs).start();

            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        } catch(NumberFormatException e) {
            status.setText("ID must be a number!");
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

        if(!choiceBox.getItems().isEmpty()) {
            choiceBox.getSelectionModel().select(0);
        }
    }
}
