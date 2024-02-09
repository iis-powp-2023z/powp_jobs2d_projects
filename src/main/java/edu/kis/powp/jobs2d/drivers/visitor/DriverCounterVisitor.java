package edu.kis.powp.jobs2d.drivers.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.drivers.PreciseLoggerDriver;
import edu.kis.powp.jobs2d.drivers.VisitableDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.TrackedJob2dDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverContainer;
import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformingDriver;

public class DriverCounterVisitor implements DriverVisitor {

    @Override
    public int visitDriverContainer(DriverContainer driverContainer) {
        int count = 1;
        for (Job2dDriver child : driverContainer.getChildren()) {
            if (child instanceof VisitableDriver) {
                count += ((VisitableDriver) child).accept(this);
            }
        }
        return count;
    }

    @Override
    public int visitLineDriverAdapter(LineDriverAdapter lineDriverAdapter) {
        return 1;
    }

    @Override
    public int visitTrackedJob2dDriver(TrackedJob2dDriver trackedJob2dDriver) {
        return 1;
    }

    @Override
    public int visitDriverMacro(DriverMacro driverMacro) {
        return 1;
    }

    @Override
    public int visitPreciseLoggerDriver(PreciseLoggerDriver preciseLoggerDriver) {
        return 1;
    }

    @Override
    public int visitTransformingDriver(TransformingDriver transformingDriver) {
        int count = 1;
        Job2dDriver childDriver = transformingDriver.getDriver();
        if (childDriver instanceof VisitableDriver) {
            count += ((VisitableDriver) childDriver).accept(this);
        }
        return count;
    }
}
