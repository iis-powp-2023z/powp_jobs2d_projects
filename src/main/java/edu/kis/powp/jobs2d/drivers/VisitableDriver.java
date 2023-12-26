package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.drivers.visitor.DriverVisitor;

public interface VisitableDriver {
    void accept(DriverVisitor visitor);
}
