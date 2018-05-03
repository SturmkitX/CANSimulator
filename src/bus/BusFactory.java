package bus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BusFactory {

    private static List<Bus> buses = new ArrayList<>();

    private BusFactory() {

    }

    public static void createBus(int id) {
        Bus b = new Bus();
        b.setId(id);
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

    public static List<Bus> getBuses() {
        return buses;
    }

    public static void setBuses(List<Bus> buses) {
        BusFactory.buses = buses;
    }

    public static void deleteBus(int id) {
        List<Integer> ids = buses.stream().map(b -> b.getId()).collect(Collectors.toList());
        if(ids.contains(id)) {
            Bus aux = new Bus();
            aux.setId(id);
            buses.remove(aux);
        }
    }


}
