package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.visitor.DriverVisitor;

public interface VisitableDriver extends Job2dDriver {
    int accept(DriverVisitor visitor);
}
