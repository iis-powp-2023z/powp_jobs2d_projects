package edu.kis.powp.jobs2d.features;

public class UsageMonitorStorage implements IUsageMonitorStorage {
    private double fullDistanceTraveled;
    private double workDistanceTraveled;

    public UsageMonitorStorage() {
        this.fullDistanceTraveled = 0;
        this.workDistanceTraveled = 0;
    }

    @Override
    public double getFullDistanceTraveled() {
        return fullDistanceTraveled;
    }

    @Override
    public double getWorkDistanceTraveled() {
        return workDistanceTraveled;
    }

    @Override
    public void reset() {
        this.fullDistanceTraveled = 0;
        this.workDistanceTraveled = 0;
    }

    @Override
    public void addFullDistanceTraveled(double distance) {
        this.fullDistanceTraveled += distance;
    }

    @Override
    public void addWorkDistanceTraveled(double distance) {
        this.workDistanceTraveled += distance;
    }
}
