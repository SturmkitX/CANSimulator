package bus;

import java.util.ArrayList;
import java.util.List;

public class BusFactory {

    private static List<Bus> buses = new ArrayList<>();

    private BusFactory() {

    }

    public static void createBus() {
        Bus b = new Bus();
        b.setId(buses.size());
        buses.add(b);
    }

    public static Bus getBus(int index) {
        Bus found = null;
        try {
            found = buses.get(index);
        } catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return found;
    }


}
