package starter;

import controller.micro.DashboardController;

import java.lang.reflect.Method;

public class Annotator {
    static DashboardController con = new DashboardController(0);

    public static void main(String[] args) {
        con.getClass().getMethods();
        for(Method m : con.getClass().getMethods()) {
            System.out.println(m.getName() + " " + m.getAnnotations().length);
        }
    }
}
