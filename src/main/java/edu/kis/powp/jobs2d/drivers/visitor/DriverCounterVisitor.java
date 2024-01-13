package edu.kis.powp.jobs2d.drivers.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.drivers.PreciseLoggerDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.TrackedJob2dDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverContainer;
import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformingDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DriverCounterVisitor implements DriverVisitor {

    private int visitDriverContainerCounter = 0;
    private int visitLineDriverAdapterCounter = 0;
    private int visitTrackedJob2dDriverCounter = 0;
    private int visitDriverMacroCounter = 0;
    private int visitPreciseLoggerDriverCounter = 0;
    private int visitTransformingDriverCounter = 0;

    public int getVisitDriverContainerCounter() {
        return visitDriverContainerCounter;
    }
    public int getVisitLineDriverAdapterCounter() {
        return visitLineDriverAdapterCounter;
    }
    public int getVisitTrackedJob2dDriverCounter() {
        return visitTrackedJob2dDriverCounter;
    }
    public int getVisitDriverMacroCounter() {
        return visitDriverMacroCounter;
    }
    public int getVisitPreciseLoggerDriverCounter() {
        return visitPreciseLoggerDriverCounter;
    }
    public int getVisitTransformingDriverCounter() {
        return visitTransformingDriverCounter;
    }

    @Override
    public void visitDriverContainer(DriverContainer driverContainer) {
        visitDriverContainerCounter += 1;
        for (Job2dDriver child : driverContainer.getChildren()) {
            try {
                Method acceptor = child.getClass().getMethod("accept", DriverVisitor.class);
                acceptor.invoke(child, this);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void visitLineDriverAdapter(LineDriverAdapter lineDriverAdapter) {
        visitLineDriverAdapterCounter += 1;
    }

    @Override
    public void visitTrackedJob2dDriver(TrackedJob2dDriver trackedJob2dDriver) {
        visitTrackedJob2dDriverCounter += 1;
    }

    @Override
    public void visitDriverMacro(DriverMacro driverMacro) {
        visitDriverMacroCounter += 1;

    }

    @Override
    public void visitPreciseLoggerDriver(PreciseLoggerDriver preciseLoggerDriver) {
        visitPreciseLoggerDriverCounter += 1;
    }

    @Override
    public void visitTransformingDriver(TransformingDriver transformingDriver) {
        visitTransformingDriverCounter += 1;
    }

    @Override
    public String toString() {
        return "DriverCounters{" +
                "visitDriverContainerCount=" + visitDriverContainerCounter +
                ", visitLineDriverAdapterCount=" + visitLineDriverAdapterCounter +
                ", visitTrackedJob2dDriverCount=" + visitTrackedJob2dDriverCounter +
                ", visitDriverMacroCount=" + visitDriverMacroCounter +
                ", visitPreciseLoggerDriverCount=" + visitPreciseLoggerDriverCounter +
                ", visitTransformingDriverCount=" + visitTransformingDriverCounter +
                '}';
    }
}
