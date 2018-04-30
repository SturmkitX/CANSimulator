package controller.sets;

public class SetMapper {

    private SetMapper() {

    }

    private static final String[] classes = new String[] {
            DashboardSet.class.getName(),
            EngineSet.class.getName()
    };

    public static String[] getClasses() {
        return classes;
    }
}
