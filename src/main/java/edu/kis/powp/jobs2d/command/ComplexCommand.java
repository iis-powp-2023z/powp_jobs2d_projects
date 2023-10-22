package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;
import java.util.List;

public class ComplexCommand implements ICompoundCommand {

    private final List<DriverCommand> listOfCommands;
    private String name;

    public ComplexCommand(List<DriverCommand> listOfCommands, String name) {
        this.listOfCommands = listOfCommands;
        this.name = name;
    }

    @Override
    public void execute(Job2dDriver driver) {
        this.iterator().forEachRemaining(command -> command.execute(driver));
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return listOfCommands.iterator();
    }

    @Override
    public String toString() {
        return name;
    }
}
