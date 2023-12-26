package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.visitor.DriverVisitor;
import edu.kis.powp.jobs2d.drivers.VisitableDriver;
import edu.kis.powp.jobs2d.features.IUsageMonitor;

import java.util.logging.Logger;

public class TrackedJob2dDriver implements Job2dDriver, VisitableDriver {
    private final Job2dDriver delegate;
    private final IUsageMonitor usageMonitor;

    private double startX = 0;
    private double startY = 0;

    public TrackedJob2dDriver(Job2dDriver delegate, IUsageMonitor usageMonitor) {
        this.delegate = delegate;
        this.usageMonitor = usageMonitor;
    }

    @Override
    public void setPosition(int x, int y) {
        delegate.setPosition(x, y);
        usageMonitor.addFullDistanceTraveled(startX, startY, x, y);
        usageMonitor.printUsage();
        updatePosition(x, y);
    }

    @Override
    public void operateTo(int x, int y) {
        delegate.operateTo(x, y);
        usageMonitor.addWorkDistanceTraveled(startX, startY, x, y);
        usageMonitor.addFullDistanceTraveled(startX, startY, x, y);
        usageMonitor.printUsage();
        updatePosition(x, y);
    }

    private void updatePosition(int x, int y) {
        startX = x;
        startY = y;
    }

    @Override
    public void accept(DriverVisitor visitor) {
        visitor.visitTrackedJob2dDriver(this);
    }
}
