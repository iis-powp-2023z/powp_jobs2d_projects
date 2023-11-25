package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.drivers.PreciseLoggerDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.composite.DriverContainer;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformingDriver;
import edu.kis.powp.jobs2d.features.MacroFeature;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers.*;

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
    private static void setupCommandTests(Application application) {
        application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());
        application.addTest("Load Triangle", new SelectTestTriangle2OptionListener());
        application.addTest("Load Rectangle",  new SelectTestRectangle2OptionListener());
        application.addTest("Load Cloned Rectangle",  new SelectTestClone2OptionListener());

        application.addTest("Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));

        application.addTest("Load macro", new SelectMacro2OptionListener());

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

        DrawPanelController drawerController = DrawerFeature.getDrawerController();

        // Driver for Line Simulator
        Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
        DriverFeature.addDriver("Line Simulator", driver);
        DriverFeature.getDriverManager().setCurrentDriver(driver);

        // Transformed Driver for Line Simulator - scaled to 25% and rotated by 70deg
        TransformingDriver ScaledAndRotatedDriver = new TransformingDriver(driver);
        ScaledAndRotatedDriver.addModifier(new ScalingModifier(0.25, 0.25));
        ScaledAndRotatedDriver.addModifier(new RotationModifier(70));
        DriverFeature.addDriver("Line Simulator  - scale: 1/4, rotation: 70deg", ScaledAndRotatedDriver);

        // Driver for Special Line Simulator
        driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
        DriverFeature.addDriver("Special line Simulator", driver);

        // Transformed Driver for Special Line Simulator - flipped in both planes and shifted axes: X(50) Y(-50)
        TransformingDriver FlippedAndShiftedDriver = new TransformingDriver(driver);
        FlippedAndShiftedDriver.addModifier(ScalingModifierFactory.createHorizontalFlipModifier());
        FlippedAndShiftedDriver.addModifier(ScalingModifierFactory.createVerticalFlipModifier());
        FlippedAndShiftedDriver.addModifier(new ShiftAxesModifier(50, -50));
        DriverFeature.addDriver("Special Line Simulator  - flipped X and Y, shifted X and Y", FlippedAndShiftedDriver);

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

                app.setVisibility(true);
            }
        });
    }

}
