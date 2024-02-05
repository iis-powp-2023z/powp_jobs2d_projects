package edu.kis.powp.jobs2d.drivers.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.drivers.PreciseLoggerDriver;
import edu.kis.powp.jobs2d.drivers.VisitableDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.TrackedJob2dDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverContainer;
import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformingDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DriverCounterVisitor implements DriverVisitor {

    private int visitDriversCounter = 0;


    public int getVisitDriversContainerCounter() {
        return visitDriversCounter;
    }

    @Override
    public void visitDriverContainer(DriverContainer driverContainer) {
        int innerDriversCounter = 0;

        for (Job2dDriver child : driverContainer.getChildren()) {
            try {
                Method acceptor = child.getClass().getMethod("accept", DriverVisitor.class);
                int beforeInvocation = this.visitDriversCounter;

                acceptor.invoke(child, this);

                int afterInvocation = this.visitDriversCounter;
                innerDriversCounter += (afterInvocation - beforeInvocation);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        this.visitDriversCounter += innerDriversCounter + 1;
    }




    @Override
    public void visitLineDriverAdapter(LineDriverAdapter lineDriverAdapter) {
        visitDriversCounter = 1;
    }

    @Override
    public void visitTrackedJob2dDriver(TrackedJob2dDriver trackedJob2dDriver) {
        visitDriversCounter = 1;
    }

    @Override
    public void visitDriverMacro(DriverMacro driverMacro) {
        visitDriversCounter = 1;

    }

    @Override
    public void visitPreciseLoggerDriver(PreciseLoggerDriver preciseLoggerDriver) {
        visitDriversCounter = 1;
    }

    @Override
    public void visitTransformingDriver(TransformingDriver transformingDriver) {
        int localCounter = 1;

        Job2dDriver childDriver = transformingDriver.getDriver();
        if (childDriver instanceof VisitableDriver) {
            int beforeVisitingChild = this.visitDriversCounter;

            ((VisitableDriver) childDriver).accept(this);

            int afterVisitingChild = this.visitDriversCounter;
            localCounter += (afterVisitingChild - beforeVisitingChild);
        }

        this.visitDriversCounter += localCounter;
    }




    @Override
    public String toString() {
        return "DriverCounters{" +
                "visitDriversCounter=" + visitDriversCounter +
                '}';
    }
}
