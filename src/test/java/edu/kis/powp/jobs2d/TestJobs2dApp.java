package edu.kis.powp.jobs2d;

import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.drivers.PreciseLoggerDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.TrackedJob2dDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverContainer;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.*;
import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformingDriver;
import edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers.RotationModifier;
import edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers.ScalingModifier;
import edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers.ScalingModifierFactory;
import edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers.ShiftAxesModifier;
import edu.kis.powp.jobs2d.gui.Extensions;
import edu.kis.powp.jobs2d.gui.extensions.LoggerListener;
import edu.kis.powp.jobs2d.gui.extensions.MacroListener;
import edu.kis.powp.jobs2d.gui.extensions.ModifierListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestJobs2dApp {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Setup test concerning preset figures in context.
     *
     * @param application Application context.
     */
    private static void setupPresetTests(Application application) {
        SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
                DriverFeature.getDriverManager());
        SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(
                DriverFeature.getDriverManager());


        application.addTest("Figure Joe 1", selectTestFigureOptionListener);
        application.addTest("Figure Joe 2", selectTestFigure2OptionListener);

    }

    /**
     * Setup test using driver commands in context.
     *
     * @param application Application context.
     */
    private static void setupCommandTests(Application application)
    {
        application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());
        application.addTest("Load Triangle", new SelectTestTriangle2OptionListener());
        application.addTest("Load Rectangle", new SelectTestRectangle2OptionListener());
        application.addTest("Load Cloned Rectangle", new SelectTestClone2OptionListener());

        application.addTest("Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));
        application.addTest("Load macro", new SelectMacro2OptionListener());
    }

    /**
     * Setup driver manager, and set default Job2dDriver for application.
     *
     * @param app Application context.
     */
    private static void setupDrivers(Application app) {

        // Driver Base for Line Simulator
        Job2dDriver basicLineDriver = new LineDriverAdapter(DrawerFeature.getDrawerController(), LineFactory.getBasicLine(), "basic");

        // Driver Base for Special Line Simulator
        LineDriverAdapter basicSpecialLineSimulator = new LineDriverAdapter(DrawerFeature.getDrawerController(), LineFactory.getSpecialLine(), "special");

        // Transforming Driver for Line Simulator
        TransformingDriver lineDriver = new TransformingDriver(basicLineDriver);

        // Transforming Driver for Special Line Simulator
        TransformingDriver specialLineDriver = new TransformingDriver(basicSpecialLineSimulator);

        // Driver for Line Simulator
        DriverFeature.addDriver("Line Simulator", lineDriver);
        DriverFeature.getDriverManager().setCurrentDriver(lineDriver);

        // Driver for Special Line Simulator
        DriverFeature.addDriver("Special Line Simulator", specialLineDriver);

        // Add modifiers to the extensions
        Extensions.addMenuElementWithCheckbox(app, "Scale: 1/4", new ModifierListener(new ScalingModifier(0.25, 0.25), lineDriver, specialLineDriver));
        Extensions.addMenuElementWithCheckbox(app, "Rotation: 70deg", new ModifierListener(new RotationModifier(70), lineDriver, specialLineDriver));
        Extensions.addMenuElementWithCheckbox(app, "Horizontal flip", new ModifierListener(ScalingModifierFactory.createHorizontalFlipModifier(), lineDriver, specialLineDriver));
        Extensions.addMenuElementWithCheckbox(app, "Vertical flip", new ModifierListener(ScalingModifierFactory.createVerticalFlipModifier(), lineDriver, specialLineDriver));
        Extensions.addMenuElementWithCheckbox(app, "Shifted X and Y", new ModifierListener(new ShiftAxesModifier(50, -50), lineDriver, specialLineDriver));
        Extensions.addMenuElementWithCheckbox(app, "Macro", new MacroListener(DriverFeature.getDriverManager()));

        DriverFeature.updateDriverInfo();
    }

    private static void setupWindows(Application application) {

        CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
        application.addWindowComponent("Command Manager", commandManager);

        CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
                commandManager);
        CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);

        DrawLinesMouseListener drawLinesMouseListener = new DrawLinesMouseListener(application);
        application.getFreePanel().addMouseListener(drawLinesMouseListener);
    }

    /**
     * Setup menu for adjusting logging settings.
     *
     * @param application Application context.
     */
    private static void setupLogger(Application application) {

        LoggerDriver loggerDriver = new LoggerDriver();
        TrackedJob2dDriver trackedJob2dDriver;
        Job2dDriver preciseLoggerDriver, loggerAndDrawerContainer;

        // Create a TrackedJob2dDriver instance
        IUsageMonitorStorage usageMonitorStorage = new UsageMonitorStorage();
        IUsageMonitor usageMonitor = new UsageMonitor(usageMonitorStorage);
        trackedJob2dDriver = new TrackedJob2dDriver(loggerDriver, usageMonitor);

        // Create a Precise Logger instance
        preciseLoggerDriver = new PreciseLoggerDriver();

        // Create a container for Precise Logger combined with Line Drawer
        loggerAndDrawerContainer = new DriverContainer(
                Arrays.asList
                        (
                                preciseLoggerDriver,
                                new LineDriverAdapter(DrawerFeature.getDrawerController(), LineFactory.getBasicLine(), "basic")
                        )
        );

        application.addComponentMenu(Logger.class, "Logger", 0);
        application.addComponentMenuElement(Logger.class, "Clear log",
                (ActionEvent e) -> application.flushLoggerOutput());
        application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
        application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
        application.addComponentMenuElement(Logger.class, "Warning level",
                (ActionEvent e) -> logger.setLevel(Level.WARNING));
        application.addComponentMenuElement(Logger.class, "Severe level",
                (ActionEvent e) -> logger.setLevel(Level.SEVERE));
        application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));

        // Add loggers to the extensions
        Extensions.addMenuElementWithCheckbox(application, "Logger", new LoggerListener(DriverFeature.getDriverManager(), loggerDriver));
        Extensions.addMenuElementWithCheckbox(application, "Tracked Job 2D", new LoggerListener(DriverFeature.getDriverManager(), trackedJob2dDriver));
        Extensions.addMenuElementWithCheckbox(application, "Precise Logger", new LoggerListener(DriverFeature.getDriverManager(), preciseLoggerDriver));
        Extensions.addMenuElementWithCheckbox(application, "Precise Logger + Line Drawer", new LoggerListener(DriverFeature.getDriverManager(), loggerAndDrawerContainer));
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Application app = new Application("Jobs 2D");
                DrawerFeature.setupDrawerPlugin(app);
                CommandsFeature.setupCommandManager();
                Extensions.setupExtensions(app);

                MacroFeature.setupMacro(app);
                DriverFeature.setupDriverPlugin(app);
                setupDrivers(app);
                setupPresetTests(app);
                setupCommandTests(app);
                setupLogger(app);
                setupWindows(app);

                app.setVisibility(true);
            }
        });
    }

}
