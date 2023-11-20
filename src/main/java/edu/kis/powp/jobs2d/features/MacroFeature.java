package edu.kis.powp.jobs2d.features;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.events.SelectMacroStartListener;
import edu.kis.powp.jobs2d.events.SelectMacroStopListener;
import edu.kis.powp.jobs2d.events.SelectMacroClearListener;

public class MacroFeature {
    private static Application app;
    private static DriverMacro driverMacro;

    public static void setDriverMacro(DriverMacro driverMacro) {
        MacroFeature.driverMacro = driverMacro;
    }
    private static boolean recording;
    public static DriverMacro getDriverMacro(){
        return driverMacro;
    }
    public static void setRecording(boolean rec){
        MacroFeature.recording = rec;
    }
    public static boolean getRecording(){
        return MacroFeature.recording;
    }
    public static void setupMacro(Application application) {
        app = application;
        app.addComponentMenu(MacroFeature.class, "Macro recording");
        app.addComponentMenuElement(MacroFeature.class, "Start", new SelectMacroStartListener());
        app.addComponentMenuElement(MacroFeature.class, "Stop", new SelectMacroStopListener());
        app.addComponentMenuElement(MacroFeature.class, "Clear", new SelectMacroClearListener());
    }
}
