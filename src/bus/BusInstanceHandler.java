package bus;

import java.util.ArrayList;
import java.util.List;

public class BusInstanceHandler {

    private static List<Bus> buses = new ArrayList<>();

    private BusInstanceHandler() {
    }

    public static void createBus() {
        buses.add(new Bus());
    }

    public static Bus getBus(int index) {
        Bus found;
        try {
            found = buses.get(index);
        } catch(IndexOutOfBoundsException e) {
            found = null;
        }

        return found;
    }
}
