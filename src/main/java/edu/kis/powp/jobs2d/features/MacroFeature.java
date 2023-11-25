package edu.kis.powp.jobs2d.features;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.events.SelectMacroStartListener;
import edu.kis.powp.jobs2d.events.SelectMacroStopListener;
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
        app.addComponentMenu(MacroFeature.class, "Macro");
        app.addComponentMenuElement(MacroFeature.class, "Start", new SelectMacroStartListener(driverMacro, DriverFeature.getDriverManager()));
        app.addComponentMenuElement(MacroFeature.class, "Stop", new SelectMacroStopListener(driverMacro, DriverFeature.getDriverManager()));
        app.addComponentMenuElement(MacroFeature.class, "Clear", new SelectMacroClearListener(MacroFeature.getDriverMacro()));
    }
}
