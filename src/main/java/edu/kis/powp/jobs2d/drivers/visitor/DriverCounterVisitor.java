package edu.kis.powp.jobs2d.drivers.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.drivers.PreciseLoggerDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.TrackedJob2dDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverContainer;
import edu.kis.powp.jobs2d.drivers.visitor.DriverVisitor;
import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformingDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DriverCounterVisitor implements DriverVisitor {

    private int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public void visitDriverContainer(DriverContainer driverContainer) {
        count += 1;
    }

    @Override
    public void visitLineDriverAdapter(LineDriverAdapter lineDriverAdapter) {
        count += 1;
    }

    @Override
    public void visitTrackedJob2dDriver(TrackedJob2dDriver trackedJob2dDriver) {
        count += 1;
    }

    @Override
    public void visitDriverMacro(DriverMacro driverMacro) {
        count += 1;

    }

    @Override
    public void visitPreciseLoggerDriver(PreciseLoggerDriver preciseLoggerDriver) {
        count += 1;
    }

    @Override
    public void visitTransformingDriver(TransformingDriver transformingDriver) {
        count += 1;
    }

    @Override
    public String toString() {
        return "DriverCounter{" +
                "count=" + count +
                '}';
    }
}
