package edu.kis.powp.jobs2d.drivers.visitor;

import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.TrackedJob2dDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverContainer;
import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformingDriver;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.drivers.PreciseLoggerDriver;

public interface DriverVisitor {
    void visitDriverContainer(DriverContainer driverContainer);

    void visitLineDriverAdapter(LineDriverAdapter lineDriverAdapter);

    void visitTrackedJob2dDriver(TrackedJob2dDriver trackedJob2dDriver);

    void visitDriverMacro(DriverMacro driverMacro);

    void visitPreciseLoggerDriver(PreciseLoggerDriver preciseLoggerDriver);

    void visitTransformingDriver(TransformingDriver transformingDriver);
}
