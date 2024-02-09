package edu.kis.powp.jobs2d.drivers.visitor;

import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.TrackedJob2dDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverContainer;
import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformingDriver;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.drivers.PreciseLoggerDriver;

public interface DriverVisitor {
    int visitDriverContainer(DriverContainer driverContainer);
    int visitLineDriverAdapter(LineDriverAdapter lineDriverAdapter);
    int visitTrackedJob2dDriver(TrackedJob2dDriver trackedJob2dDriver);
    int visitDriverMacro(DriverMacro driverMacro);
    int visitPreciseLoggerDriver(PreciseLoggerDriver preciseLoggerDriver);
    int visitTransformingDriver(TransformingDriver transformingDriver);
}

