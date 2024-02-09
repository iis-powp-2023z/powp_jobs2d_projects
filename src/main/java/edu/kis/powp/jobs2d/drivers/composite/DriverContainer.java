package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.VisitableDriver;
import edu.kis.powp.jobs2d.drivers.visitor.DriverVisitor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverContainer implements Job2dDriver, VisitableDriver {
    private List<Job2dDriver> children = new ArrayList<>();

    public DriverContainer(List<Job2dDriver> children) {
        this.children = children;
    }

    public DriverContainer() {}

    @Override
    public void operateTo(int x, int y) {
        for (Job2dDriver driver : children) {
            Job2dDriver dr = (Job2dDriver) driver;
            dr.operateTo(x, y);
        }
    }

    @Override
    public void setPosition(int x, int y) {
        for (Job2dDriver driver : children) {
            Job2dDriver dr = (Job2dDriver) driver;
            dr.setPosition(x, y);
        }
    }

    public void add(Job2dDriver driver) {
        children.add(driver);
    }

    public void remove(Job2dDriver driver) {
        children.remove(driver);
    }

    public List<Job2dDriver> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public int accept(DriverVisitor visitor) {
        return visitor.visitDriverContainer(this);
    }
}
