package edu.kis.powp.jobs2d.gui;

import edu.kis.powp.appbase.Application;

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
}
