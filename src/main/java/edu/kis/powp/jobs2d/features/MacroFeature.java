package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.events.SelectMacroClearListener;

public class MacroFeature {
    private static Application app;
    private static DriverMacro driverMacro;

    public static void setDriverMacro() {
        MacroFeature.driverMacro = new DriverMacro();
    }

    public static DriverMacro getDriverMacro(){
        return driverMacro;
    }

    public static void setupMacro(Application application) {
        app = application;
        driverMacro = new DriverMacro();
    }
}
