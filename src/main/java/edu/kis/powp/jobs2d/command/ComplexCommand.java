package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;
import java.util.List;

public class ComplexCommand implements ICompoundCommand {

    private final List<DriverCommand> listOfCommands;

    public ComplexCommand(List<DriverCommand> listOfCommands) {
        this.listOfCommands = listOfCommands;
    }

    @Override
    public void execute(Job2dDriver driver) {
        this.iterator().forEachRemaining(command -> command.execute(driver));
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return listOfCommands.iterator();
    }
}
