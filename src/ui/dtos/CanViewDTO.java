package ui.dtos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CanViewDTO {

    private IntegerProperty microId;
    private IntegerProperty canId;
    private IntegerProperty busId;

    public CanViewDTO(int microId, int canId, int busId) {
        this.microId = new SimpleIntegerProperty(microId);
        this.canId = new SimpleIntegerProperty(canId);
        this.busId = new SimpleIntegerProperty(busId);
    }

    public int getMicroId() {
        return microId.get();
    }

    public IntegerProperty microIdProperty() {
        return microId;
    }

    public int getCanId() {
        return canId.get();
    }

    public IntegerProperty canIdProperty() {
        return canId;
    }

    public int getBusId() {
        return busId.get();
    }

    public IntegerProperty busIdProperty() {
        return busId;
    }
}
