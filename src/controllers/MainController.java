package controllers;

import bus.Bus;
import bus.BusFactory;
import controller.Controller;
import controller.MicroController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.dtos.ComponentDTO;
import utils.UserSession;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private ComboBox<Integer> componentCanId; // Value injected by FXMLLoader

    @FXML // fx:id="componentMicroId"
    private Text componentMicroId; // Value injected by FXMLLoader

    @FXML // fx:id="logText"
    private TextArea logText; // Value injected by FXMLLoader

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

    @FXML // fx:id="createBusMenu"
    private MenuItem createBusMenu; // Value injected by FXMLLoader

    @FXML // fx:id="deleteBusMenu"
    private MenuItem deleteBusMenu; // Value injected by FXMLLoader

    @FXML // fx:id="attachBusMenu"
    private MenuItem attachBusMenu; // Value injected by FXMLLoader

    @FXML // fx:id="detachBusMenu"
    private MenuItem detachBusMenu; // Value injected by FXMLLoader

    @FXML // fx:id="attachmentMenu"
    private MenuItem attachmentMenu; // Value injected by FXMLLoader

    @FXML
    void closeAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void insertComponent(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../ui/add_comp.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Add component");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void delComponent(ActionEvent event) {
        ComponentDTO selected = drawnComponents.getSelectionModel().getSelectedItem();
        if(selected != null) {
            componentList.remove(selected);
        }
        System.out.println(componentList.size() + " " + drawnComponents.getItems().size());

    }

    @FXML
    void loadObjects(ActionEvent event) {
        try {
            // open file chooser and select a file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose object file");
            Stage stage = (Stage) componentCanId.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);
            if(file == null) {
                return;
            }

            ObjectInput fin = new ObjectInputStream(new FileInputStream(file));
            List<MicroController> comps = (ArrayList<MicroController>) fin.readObject();
            List<Bus> buses = (ArrayList<Bus>) fin.readObject();
            fin.close();

            buses.stream().forEach(b -> b.initializeTransient());
            BusFactory.setBuses(buses);

            componentList.clear();
            for(MicroController cs : comps) {
                cs.initializeTransientFields();

                // restore observer states
                for(Controller c : cs.getCans().values()) {
                    c.addObserver(cs);
                    c.getBus().addObserver(c);
                }
                componentList.add(new ComponentDTO(cs));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveObjects(ActionEvent event) {
        List<MicroController> comps = new ArrayList<>();
        for(ComponentDTO dto : componentList) {
            comps.add(dto.getSource());
        }

        try {
            // open file chooser and select a file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose object file");
            Stage stage = (Stage) componentCanId.getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);
            if(file == null) {
                return;
            }

            ObjectOutput fout = new ObjectOutputStream(new FileOutputStream(file));
            fout.writeObject(comps);
            fout.writeObject(BusFactory.getBuses());
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        componentList = UserSession.getComponents();
        drawnComponents.setItems(componentList);

//        BusFactory.createBus();

        drawnComponents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ComponentDTO>() {
            @Override
            public void changed(ObservableValue<? extends ComponentDTO> observable, ComponentDTO oldValue, ComponentDTO newValue) {
                if(drawnComponents.getSelectionModel().isEmpty()) {
                    return;
                }

                System.out.println(newValue);
                componentName.setText(newValue.getSource().getClass().getSimpleName());
                componentCanId.setItems(newValue.getBusIds());
                componentMicroId.setText("" + newValue.getMicroId());
                componentActions.setItems(newValue.buttonsProperty());

                UserSession.setCurrentMicro(newValue);
            }
        });

        logText.textProperty().bind(UserSession.logProperty());

        componentCanId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                if(!componentCanId.getSelectionModel().isEmpty()) {
                    UserSession.setCurrentBus(BusFactory.getBus(newValue));
                }

            }
        });

    }

    @FXML
    void attachBus(ActionEvent event) {
        if(drawnComponents.getSelectionModel().isEmpty()) {
            return;
        }



        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../ui/attach_bus.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Attach Bus");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void createBus(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../ui/add_bus.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Add bus");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void deleteBus(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../ui/del_bus.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Delete bus");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void detachBus(ActionEvent event) {
        if(drawnComponents.getSelectionModel().isEmpty()) {
            return;
        }

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../ui/detach_bus.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Detach bus");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewAttachments(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../ui/view_cans.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("View attachments");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
