package edu.kis.powp.jobs2d.gui;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.TrackedJob2dDriver;
import edu.kis.powp.jobs2d.events.SelectMacro2OptionListener;
import edu.kis.powp.jobs2d.events.SelectMacroStartListener;
import edu.kis.powp.jobs2d.events.SelectMacroStopListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentCommandOptionListener;
import edu.kis.powp.jobs2d.features.*;
import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformationModifier;
import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformingDriver;
import edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers.RotationModifier;
import edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers.ScalingModifier;
import edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers.ScalingModifierFactory;
import edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers.ShiftAxesModifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Extensions
{
    private static TransformingDriver lineTransformingDriver;
    private static TransformingDriver specialLineTransformingDriver;
    private static LoggerDriver loggerDriver;
    private static TrackedJob2dDriver trackedJob2dDriver;

    static
    {
        DrawPanelController drawerController = DrawerFeature.getDrawerController();

        // Driver for Line Simulator
        Job2dDriver lineDriver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");

        // Driver for Special Line Simulator
        LineDriverAdapter specialLineSimulator = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");

        // Transforming Driver for Line Simulator
        lineTransformingDriver = new TransformingDriver(lineDriver);

        // Transforming Driver for Special Line Simulator
        specialLineTransformingDriver = new TransformingDriver(specialLineSimulator);

        // Create a logger instance
        loggerDriver = new LoggerDriver();

        // Create a TrackedJob2dDriver instance
        IUsageMonitorStorage usageMonitorStorage = new UsageMonitorStorage();
        IUsageMonitor usageMonitor = new UsageMonitor(usageMonitorStorage);
        trackedJob2dDriver = new TrackedJob2dDriver(loggerDriver, usageMonitor);
    }

    public static void setupExtensions(Application app)
    {
        // Add a new menu
        app.addComponentMenu(Extensions.class, "Extensions");

        // Add various extensions
        app.addComponentMenuElement(Extensions.class, "Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));
        app.addComponentMenuElement(Extensions.class, "Load macro", new SelectMacro2OptionListener());
        addMenuElementWithCheckbox(app, "Logger", new LoggerListener(DriverFeature.getDriverManager(), loggerDriver));
        addMenuElementWithCheckbox(app, "Tracked Job 2D", new LoggerListener(DriverFeature.getDriverManager(), trackedJob2dDriver));

        // Add modifiers
        addMenuElementWithCheckbox(app, "Scale: 1/4", new ModifierListener(new ScalingModifier(0.25, 0.25)));
        addMenuElementWithCheckbox(app, "Rotation: 70deg", new ModifierListener(new RotationModifier(70)));
        addMenuElementWithCheckbox(app, "Horizontal flip", new ModifierListener(ScalingModifierFactory.createHorizontalFlipModifier()));
        addMenuElementWithCheckbox(app, "Vertical flip", new ModifierListener(ScalingModifierFactory.createVerticalFlipModifier()));
        addMenuElementWithCheckbox(app, "Shifted X and Y", new ModifierListener(new ShiftAxesModifier(50, -50)));
        addMenuElementWithCheckbox(app, "Macro", new MacroListener(DriverFeature.getDriverManager()));
    }

    private static void addMenuElementWithCheckbox(Application app, String label, ActionListener listener)
    {
        app.addComponentMenuElementWithCheckBox(Extensions.class, label, listener, false);
    }

    private static class LoggerListener implements ActionListener
    {
        private DriverManager driverManager;
        private Job2dDriver driver;
        private boolean enabled;

        public LoggerListener(DriverManager driverManager, Job2dDriver driver)
        {
            enabled = false;
            this.driverManager = driverManager;
            this.driver = driver;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            enabled = !enabled;

            if (enabled)
            {
                driverManager.addDriver(driver);
            }
            else
            {
                driverManager.removeDriver(driver);
            }

            DriverFeature.updateDriverInfo();
        }
    }

    private static class ModifierListener implements ActionListener
    {
        private boolean enabled;
        private TransformationModifier modifier;

        public ModifierListener(TransformationModifier modifier)
        {
            enabled = false;
            this.modifier = modifier;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            enabled = !enabled;

            if (enabled)
            {
                lineTransformingDriver.addModifier(modifier);
                specialLineTransformingDriver.addModifier(modifier);
            }
            else
            {
                lineTransformingDriver.removeModifier(modifier);
                specialLineTransformingDriver.removeModifier(modifier);
            }
        }
    }

    private static class MacroListener implements ActionListener
    {
        private boolean enabled;
        private DriverMacro driverMacro;
        private ActionListener startMacro, stopMacro;

        public MacroListener(DriverManager driverManager)
        {
            enabled = false;
            driverMacro = new DriverMacro();
            startMacro = new SelectMacroStartListener(driverMacro, driverManager);
            stopMacro = new SelectMacroStopListener(driverMacro, driverManager);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            enabled = !enabled;

            if (enabled)
            {
                startMacro.actionPerformed(e);
            }
            else
            {
                stopMacro.actionPerformed(e);
            }
        }
    }

    public static TransformingDriver getLineSimulator()
    {
        return lineTransformingDriver;
    }

    public static TransformingDriver getSpecialLineSimulator()
    {
        return specialLineTransformingDriver;
    }
}
