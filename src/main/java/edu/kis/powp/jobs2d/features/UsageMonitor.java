package edu.kis.powp.jobs2d.features;

import java.util.logging.Logger;

public class UsageMonitor implements IUsageMonitor {
    private final IUsageMonitorStorage usageMonitorStorage;

    private final static Logger logger = Logger.getLogger("global");

    public UsageMonitor(IUsageMonitorStorage usageMonitorStorage) {
        this.usageMonitorStorage = usageMonitorStorage;
        usageMonitorStorage.reset();
    }

    @Override
    public double getFullDistanceTraveled() {
        return usageMonitorStorage.getFullDistanceTraveled();
    }

    @Override
    public double getWorkDistanceTraveled() {
        return usageMonitorStorage.getWorkDistanceTraveled();
    }

    @Override
    public void reset() {
        usageMonitorStorage.reset();
    }

    @Override
    public void addFullDistanceTraveled(double distance) {
        usageMonitorStorage.addFullDistanceTraveled(distance);

    }

    @Override
    public void addWorkDistanceTraveled(double distance) {
        usageMonitorStorage.addWorkDistanceTraveled(distance);
    }

    @Override
    public void addFullDistanceTraveled(double x, double y) {
        usageMonitorStorage.addFullDistanceTraveled(calculateDistance(x, y));
    }

    @Override
    public void addWorkDistanceTraveled(double x, double y) {
        usageMonitorStorage.addWorkDistanceTraveled(calculateDistance(x, y));
    }

    public void printUsage() {
        logger.info("Full distance traveled: " + usageMonitorStorage.getFullDistanceTraveled());
        logger.info("Work distance traveled: " + usageMonitorStorage.getWorkDistanceTraveled());
    }

    private double calculateDistance(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
