package ui.dtos;

import controller.Controller;
import controller.MicroController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import utils.UserSession;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ComponentDTO implements Serializable {
    private MicroController source;
    private ListProperty<Integer> canIds;
    private IntegerProperty microId;
    private ListProperty<Button> buttons;

    public ComponentDTO(MicroController src) {
        this.source = src;
        this.canIds = new SimpleListProperty<>();
        this.microId = new SimpleIntegerProperty(src.getId());
        this.buttons = new SimpleListProperty<>();

        // System.out.println(source.getClass().getSimpleName());

        canIds.set(FXCollections.observableList(new ArrayList<>()));
        for(Controller c : src.getCans()) {
            canIds.add(c.getId());
        }

        buttons.setValue(FXCollections.observableList(new ArrayList<>()));
        computeButtons();
    }

    public MicroController getSource() {
        return source;
    }

    public void setSource(MicroController source) {
        this.source = source;
    }

    public ObservableList<Integer> getCanIds() {
        return canIds.get();
    }

    public ListProperty<Integer> canIdsProperty() {
        return canIds;
    }

    public void setCanIds(ObservableList<Integer> canIds) {
        this.canIds.set(canIds);
    }

    public int getMicroId() {
        return microId.get();
    }

    public IntegerProperty microIdProperty() {
        return microId;
    }

    public void setMicroId(int microId) {
        this.microId.set(microId);
    }

    public ObservableList<Button> getButtons() {
        return buttons.get();
    }

    public ListProperty<Button> buttonsProperty() {
        return buttons;
    }

    public void setButtons(ObservableList<Button> buttons) {
        this.buttons.set(buttons);
    }

    public String toString() {
        return String.format("Class: %s ... Micro Id : %d\n", source.getClass().getSimpleName(), microId.get());
    }

    private void computeButtons() {
        for(Method m : source.getClass().getMethods()) {
//            System.out.println(m.getName() + " " + m.getAnnotations().length);
            for(Annotation a : m.getAnnotations()) {
//                System.out.println(m.getName() + " " + a);
                if(a.toString().equals("@controller.micro.MenuAccess()")) {
                    Button b = new Button();
                    b.setText(m.getName());
                    b.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                m.invoke(source);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    buttons.add(b);
                }
            }
        }
    }
}
