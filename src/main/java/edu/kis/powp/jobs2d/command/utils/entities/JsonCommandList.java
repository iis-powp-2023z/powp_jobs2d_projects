package edu.kis.powp.jobs2d.command.utils.entities;

import edu.kis.powp.jobs2d.command.ComplexCommandBuilder;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.List;

public class JsonCommandList {
    private List<JsonCommand> commands;

    private String name;

    public List<JsonCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<JsonCommand> commands) {
        this.commands = commands;
    }

    public DriverCommand toDriverCommand() {
        ComplexCommandBuilder builder = new ComplexCommandBuilder();
        for (JsonCommand command : commands) {
            if (command.getType().equals(JsonCommand.OPERATE_TO_COMMAND_TYPE)) {
                builder.addCommand(new OperateToCommand(command.getX(), command.getY()));
            } else if (command.getType().equals(JsonCommand.SET_POSITION_COMMAND_TYPE)) {
                builder.addCommand(new SetPositionCommand(command.getX(), command.getY()));
            } else {
                throw new RuntimeException("Invalid command type: " + command.getType());
            }
        }
        return builder.build(name);
    }

    @Override
    public String toString() {
        return "JsonCommandList{" +
                "commands=" + commands +
                '}';
    }
}
