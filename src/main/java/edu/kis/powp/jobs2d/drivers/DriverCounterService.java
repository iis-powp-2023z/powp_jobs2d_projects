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

    public Map<String, Integer> getCounts() {
        DriverCounterVisitor counter = new DriverCounterVisitor();
        for (Job2dDriver driver : driverManager.getAllDrivers()) {
            if (driver instanceof VisitableDriver) {
                ((VisitableDriver) driver).accept(counter);
            }
        }
        return Collections.singletonMap("VisitDrivers", counter.getVisitDriversContainerCounter());
        }
        public void logDriverCounts(Logger logger) {
            Map<String, Integer> counts = getCounts();
            counts.forEach((type, count) -> logger.info(type + " count: " + count));
        }

}


