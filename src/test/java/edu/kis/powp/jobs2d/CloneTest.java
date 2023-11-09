package edu.kis.powp.jobs2d;
import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.ComplexCommandBuilder;
import edu.kis.powp.jobs2d.command.ComplexCommandFactory;
import edu.kis.powp.jobs2d.command.DriverCommand;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class CloneTest {
    @Test
    public void testClone() {
        List<DriverCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(ComplexCommandFactory.getRectangle());
        listOfCommands.add(ComplexCommandFactory.getTriangle());


        ComplexCommand commandToNest = new ComplexCommand(listOfCommands, "nestedCommands");

        listOfCommands = new ArrayList<>();
        listOfCommands.add(ComplexCommandFactory.getRectangle());
        listOfCommands.add(ComplexCommandFactory.getTriangle());


        ComplexCommand commandToNest2 = new ComplexCommand(listOfCommands, "nestedCommands2");

        listOfCommands = new ArrayList<>();
        listOfCommands.add(commandToNest);
        listOfCommands.add(commandToNest2);


        ComplexCommand commandToClone = new ComplexCommand(listOfCommands, "commandToClone");

        ComplexCommand clonedCommand = commandToClone.clone();

        assertNotSame(commandToClone, clonedCommand);

        assertEquals(commandToClone.getName(), clonedCommand.getName());

        for (int i = 0; i < commandToClone.getListOfCommands().size(); i++) {
            assertEquals(commandToClone.getListOfCommands().get(i), clonedCommand.getListOfCommands().get(i));
        }
    }
}