package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.CanvasBoundaryCheckVisitor;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitor;
import edu.kis.powp.jobs2d.features.DrawerFeature;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class ComplexCommand implements ICompoundCommand {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private List<DriverCommand> listOfCommands;
    private String name;

    public ComplexCommand(List<DriverCommand> listOfCommands, String name) {
        this.listOfCommands = listOfCommands;
        this.name = name;
    }

    @Override
    public void execute(Job2dDriver driver) {
        Dimension canvasDimension = DrawerFeature.getCanvasDrawAreaPanelController().getCanvasDimension();
        CanvasBoundaryCheckVisitor boundaryCheckVisitor = new CanvasBoundaryCheckVisitor(canvasDimension);
        this.accept(boundaryCheckVisitor);

        if (boundaryCheckVisitor.doesExceedCanvas()) {
            logger.warning("Drawing exceeds boundaries of canvas");
            return;
        }

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

    @Override
    public ComplexCommand clone() {
        ComplexCommand complexCommand;
        try {
            complexCommand = (ComplexCommand) super.clone();
            complexCommand.listOfCommands = new ArrayList<>();
            complexCommand.name = this.name;
            for (DriverCommand cmd : this.listOfCommands) {
                complexCommand.listOfCommands.add(cmd.clone());
            }
        } catch (CloneNotSupportedException e) {
            complexCommand = new ComplexCommand(this.listOfCommands, this.name);
        }
        return complexCommand;
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visitComplexCommand(this);
    }

    public List<DriverCommand> getListOfCommands() {
        return listOfCommands;
    }

    public String getName() {
        return name;
    }
}
