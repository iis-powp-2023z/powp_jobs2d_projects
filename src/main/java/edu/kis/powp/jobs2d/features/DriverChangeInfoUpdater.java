package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.observer.Subscriber;

public class DriverChangeInfoUpdater implements Subscriber {

    private Application app;
    private DriverManager driverManager;

    public DriverChangeInfoUpdater(Application app, DriverManager driverManager) {
        this.app = app;
        this.driverManager = driverManager;
    }

    @Override
    public void update() {
        DriverFeature.updateDriverInfo();
    }
}