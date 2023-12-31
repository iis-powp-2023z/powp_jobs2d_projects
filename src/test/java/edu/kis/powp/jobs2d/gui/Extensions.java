package edu.kis.powp.jobs2d.gui;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.DriverFeature;

public class Extensions
{
    public static void setupExtensions(Application application)
    {
        application.addComponentMenu(Extensions.class, "Extensions");

        application.addComponentMenuElement(Extensions.class, "Load secret command", new SelectLoadSecretCommandOptionListener());
        application.addComponentMenuElement(Extensions.class, "Load Triangle", new SelectTestTriangle2OptionListener());
        application.addComponentMenuElement(Extensions.class, "Load Rectangle", new SelectTestRectangle2OptionListener());
        application.addComponentMenuElement(Extensions.class, "Load Cloned Rectangle", new SelectTestClone2OptionListener());
        application.addComponentMenuElement(Extensions.class, "Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));
        application.addComponentMenuElement(Extensions.class, "Load macro", new SelectMacro2OptionListener());
    }
}
