package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.visitor.DriverCounterVisitor;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

public class DriverCounterService {
    private DriverManager driverManager;

    public DriverCounterService(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public int getTotalDriverCount() {
        int total = 0;
        DriverCounterVisitor counter = new DriverCounterVisitor();
        for (Job2dDriver driver : driverManager.getAllDrivers()) {
            if (driver instanceof VisitableDriver) {
                total += ((VisitableDriver) driver).accept(counter);
            }
        }
        return total;
    }

    public void logDriverCounts(Logger logger) {
        int total = getTotalDriverCount();
        logger.info("Total Visitable Drivers count: " + total);
    }
}



