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
import edu.kis.powp.jobs2d.gui.Extensions;

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
        application.addTest("Macro Clear", new SelectMacroClearListener(MacroFeature.getDriverMacro()));
    }

    /**
     * Setup driver manager, and set default Job2dDriver for application.
     *
     * @param application Application context.
     */
    private static void setupDrivers(Application application) {

        Job2dDriver loggerDriver = new LoggerDriver();
        DriverFeature.addDriver("Logger driver", loggerDriver);

        Job2dDriver preciousLoggerDriver = new PreciseLoggerDriver();
        DriverFeature.addDriver("Precise logger driver", preciousLoggerDriver);
        Job2dDriver driverContainer = new DriverContainer(
                Arrays.asList(
                        new PreciseLoggerDriver(),
                        new LineDriverAdapter(DrawerFeature.getDrawerController(), LineFactory.getBasicLine(), "basic")
                )
        );
        DriverFeature.addDriver("Precise Logger + Line Drawer", driverContainer);

        IUsageMonitorStorage usageMonitorStorage = new UsageMonitorStorage();
        IUsageMonitor usageMonitor = new UsageMonitor(usageMonitorStorage);
        TrackedJob2dDriver trackedJob2dDriver = new TrackedJob2dDriver(loggerDriver, usageMonitor);
        DriverFeature.addDriver("Tracked job 2d driver", trackedJob2dDriver);

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

                MacroFeature.setupMacro(app);
                DriverFeature.setupDriverPlugin(app);
                setupDrivers(app);
                setupPresetTests(app);
                setupCommandTests(app);
                setupLogger(app);
                setupWindows(app);
                Extensions.setupExtensions(app);

                app.setVisibility(true);
            }
        });
    }

}
