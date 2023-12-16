package edu.kis.powp.jobs2d.features;

public interface IUsageMonitorStorage {
    double getFullDistanceTraveled();

    double getWorkDistanceTraveled();

    void reset();

    void addFullDistanceTraveled(double distance);

    void addWorkDistanceTraveled(double distance);
}
