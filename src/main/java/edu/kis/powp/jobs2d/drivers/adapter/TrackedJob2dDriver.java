package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.IUsageMonitor;

import java.util.logging.Logger;

public class TrackedJob2dDriver implements Job2dDriver {
    private final Job2dDriver delegate;
    private final IUsageMonitor usageMonitor;

    public TrackedJob2dDriver(Job2dDriver delegate, IUsageMonitor usageMonitor) {
        this.delegate = delegate;
        this.usageMonitor = usageMonitor;
    }

    @Override
    public void setPosition(int x, int y) {
        delegate.setPosition(x, y);
        usageMonitor.addFullDistanceTraveled(0, 0, x, y);
        usageMonitor.printUsage();
    }

    @Override
    public void operateTo(int x, int y) {
        delegate.operateTo(x, y);
        usageMonitor.addWorkDistanceTraveled(0, 0, x, y);
        usageMonitor.addFullDistanceTraveled(0, 0, x, y);
        usageMonitor.printUsage();
    }
}
