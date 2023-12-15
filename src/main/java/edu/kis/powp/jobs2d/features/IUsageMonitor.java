package edu.kis.powp.jobs2d.features;

public interface IUsageMonitor {
    double getFullDistanceTraveled();

    double getWorkDistanceTraveled();

    void reset();

    void addFullDistanceTraveled(double distance);

    void addWorkDistanceTraveled(double distance);

    void addFullDistanceTraveled(double x1, double y1, double x2, double y2);

    void addWorkDistanceTraveled(double x1, double y1, double x2, double y2);

    void printUsage();
}
