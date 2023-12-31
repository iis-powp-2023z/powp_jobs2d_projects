package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;

public class Extensions {

    private static Application app;

    public static void setupExtensions(Application application) {
        app = application;
        app.addComponentMenu(Extensions.class, "Extensions");
        app.addComponentMenuElementWithCheckBox(Extensions.class, "inner option",
                (e) -> CommandsFeature.setupCommandManager(), false);
    }
}
