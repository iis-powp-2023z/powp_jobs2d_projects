package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.List;

public class ComplexCommandBuilder {
    private final List<DriverCommand> complexCommandList = new ArrayList<>();
    public ComplexCommandBuilder addCommand(DriverCommand driverCommand) {
        complexCommandList.add(driverCommand);
        return this;
    }
    public ComplexCommand build(String name) {
        return new ComplexCommand(complexCommandList, name);
    }
}
