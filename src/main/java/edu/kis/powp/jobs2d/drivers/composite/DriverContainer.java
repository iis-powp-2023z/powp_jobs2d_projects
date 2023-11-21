package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverContainer implements Job2dDriver {
    private List<Job2dDriver> children = new ArrayList<>();

    public DriverContainer(List<Job2dDriver> children) {
        this.children = children;
    }

    public DriverContainer() {}

    @Override
    public void operateTo(int x, int y) {
        for (Job2dDriver driver : children) {
            driver.operateTo(x, y);
        }
    }

    @Override
    public void setPosition(int x, int y) {
        for (Job2dDriver driver : children) {
            driver.setPosition(x, y);
        }
    }

    public void add(Job2dDriver driver) {
        children.add(driver);
    }

    public void remove(Job2dDriver driver) {
        children.remove(driver);
    }
}
