package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.BoundaryCheckJob2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

import java.util.logging.Logger;

public class CommandBoundaryCheckObserver implements Subscriber {
    private final CommandManager commandManager;

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public CommandBoundaryCheckObserver(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.commandManager.getChangePublisher().addSubscriber(this);
    }

    @Override
    public void update() {
        Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();
        BoundaryCheckJob2dDriver boundaryCheckDriver = new BoundaryCheckJob2dDriver(currentDriver);
        commandManager.getCurrentCommand().execute(boundaryCheckDriver);

        if (boundaryCheckDriver.doesExceedCanvas()) {
            logger.warning("Operation exceeds boundaries of canvas");
        }
    }
}
