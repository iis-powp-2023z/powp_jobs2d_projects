package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverContainer;
import edu.kis.powp.jobs2d.drivers.visitor.DriverCounterVisitor;
import edu.kis.powp.observer.Publisher;

import java.util.logging.Logger;

/**
 * Driver manager provides means to setup the driver. It also enables other
 * components and features of the application to react on configuration changes.
 */
public class DriverManager {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Job2dDriver currentDriver = new LoggerDriver();
    private Publisher driverChangePublisher = new Publisher();

    private DriverContainer container = new DriverContainer();

    /**
     * Set the driver as the current driver and notify all subscribers of the change.
     *
     * @param driver The driver to be set as the current driver.
     */
    public synchronized void setCurrentDriver(Job2dDriver driver) {
        container.remove(currentDriver);
        container.add(driver);
        currentDriver = driver;
        DriverCounterVisitor counter = new DriverCounterVisitor();
        VisitableDriver dr = (VisitableDriver) driver;
        dr.accept(counter);
        logger.info(counter.toString());
        driverChangePublisher.notifyObservers();
    }

    /**
     * @return Current driver.
     */
    public synchronized Job2dDriver getCurrentDriver() {
        return container;
    }

    /**
     * Get the publisher responsible for managing subscribers and notifying them of driver changes.
     *
     * @return The driver change publisher.
     */
    public Publisher getDriverChangePublisher() {
        return driverChangePublisher;
    }

    public synchronized void addDriver(Job2dDriver driver) {
        container.add(driver);
        driverChangePublisher.notifyObservers();
    }

    public synchronized void removeDriver(Job2dDriver driver) {
        container.remove(driver);
        driverChangePublisher.notifyObservers();
    }
}
