package ui.dtos;

import controller.sets.ComponentSet;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ComponentDTO {
    private ComponentSet source;
    private IntegerProperty canId;
    private IntegerProperty microId;
    private ListProperty<Button> buttons;

    public ComponentDTO(ComponentSet src) {
        this.source = src;
        this.canId = new SimpleIntegerProperty(src.getCan().getId());
        this.microId = new SimpleIntegerProperty(src.getMicro().getId());
        this.buttons = new SimpleListProperty<>();

        System.out.println(source.getClass().getSimpleName());

        buttons.setValue(FXCollections.observableList(new ArrayList<>()));
        computeButtons();
    }

    public int getCanId() {
        return canId.get();
    }

    public IntegerProperty canIdProperty() {
        return canId;
    }

    public int getMicroId() {
        return microId.get();
    }

    public IntegerProperty microIdProperty() {
        return microId;
    }

    public ObservableList<Button> getButtons() {
        return buttons.get();
    }

    public ListProperty<Button> buttonsProperty() {
        return buttons;
    }

    public ComponentSet getSource() {
        return source;
    }

    public String toString() {
        return String.format("Class: %s ... CAN Id : %d ... Micro Id : %d\n", source.getClass().getSimpleName(), canId.get(), microId.get());
    }

    private void computeButtons() {
        for(Method m : source.getMicro().getClass().getMethods()) {
            System.out.println(m.getName() + " " + m.getAnnotations().length);
            for(Annotation a : m.getAnnotations()) {
                System.out.println(m.getName() + " " + a);
                if(a.toString().equals("@controller.micro.MenuAccess()")) {
                    Button b = new Button();
                    b.setText(m.getName());
                    b.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                m.invoke(source.getMicro());
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
