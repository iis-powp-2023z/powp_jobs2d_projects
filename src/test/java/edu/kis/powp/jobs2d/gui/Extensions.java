package edu.kis.powp.jobs2d.gui;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.gui.extensions.ExtendingDriverListener;

import java.awt.event.ActionListener;

public class Extensions
{
    public static void setupExtensions(Application app)
    {
        // Add a new menu
        app.addComponentMenu(Extensions.class, "Extensions");
    }

    public static void addComponentMenuElement(Application app, String label, ActionListener listener)
    {
        app.addComponentMenuElement(Extensions.class, label, listener);
    }

    public static void addMenuElementWithCheckbox(Application app, String label, ActionListener listener)
    {
        app.addComponentMenuElementWithCheckBox(Extensions.class, label, listener, false);
    }

    public static void addExtendingDriver(Application app, String label, Job2dDriver driver) {
        addMenuElementWithCheckbox(app, label, new ExtendingDriverListener(DriverFeature.getDriverManager(), driver));
    }
}
