package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;
import edu.kis.powp.jobs2d.observer.Publisher;

/**
 * Driver manager provides means to setup the driver. It also enables other
 * components and features of the application to react on configuration changes.
 */
public class DriverManager {

    private Job2dDriver currentDriver = new LoggerDriver();
    private Publisher driverChangePublisher = new Publisher();

    /**
     * Set the driver as the current driver and notify all subscribers of the change.
     *
     * @param driver The driver to be set as the current driver.
     */
    public synchronized void setCurrentDriver(Job2dDriver driver) {
        currentDriver = driver;
        driverChangePublisher.notifyObservers();
    }

    /**
     * @return Current driver.
     */
    public synchronized Job2dDriver getCurrentDriver() {
        return currentDriver;
    }

    /**
     * Get the publisher responsible for managing subscribers and notifying them of driver changes.
     *
     * @return The driver change publisher.
     */
    public Publisher getDriverChangePublisher() {
        return driverChangePublisher;
    }
}
