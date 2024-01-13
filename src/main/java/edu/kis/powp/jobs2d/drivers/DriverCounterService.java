package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.visitor.DriverCounterVisitor;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Integer> counts = new HashMap<>();
        counts.put("LineDriverAdapter", counter.getVisitLineDriverAdapterCounter());
        counts.put("TrackedJob2dDriver", counter.getVisitTrackedJob2dDriverCounter());
        counts.put("DriverMacro", counter.getVisitDriverMacroCounter());
        counts.put("DriverContainer", counter.getVisitDriverContainerCounter());
        counts.put("PreciseLoggerDriver", counter.getVisitPreciseLoggerDriverCounter());
        counts.put("TransformingDriver", counter.getVisitTransformingDriverCounter());
        return counts;
        }
    }


