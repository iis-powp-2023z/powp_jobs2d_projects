package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.List;

public class PrintCommandVisitor implements CommandVisitor {

    private StringBuilder commandStringBuilder = new StringBuilder();

    @Override
    public void visitComplexCommand(ComplexCommand complexCommand) {
        List<DriverCommand> commandsList = complexCommand.getListOfCommands();
        for (DriverCommand cmd : commandsList) {
            cmd.accept(this);
        }
    }

    @Override
    public void visitOperateToCommand(OperateToCommand operateToCommand) {
        commandStringBuilder.append("OperateToCommand: (")
                .append(operateToCommand.getPosX())
                .append(", ")
                .append(operateToCommand.getPosY())
                .append(")\n");
    }

    @Override
    public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
        commandStringBuilder.append("SetPositionCommand: (")
                .append(setPositionCommand.getPosX())
                .append(", ")
                .append(setPositionCommand.getPosY())
                .append(")\n");
    }

    public String getCommandsListString() {
        return commandStringBuilder.toString();
    }
}
