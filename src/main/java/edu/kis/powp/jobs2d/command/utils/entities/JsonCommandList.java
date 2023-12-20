package edu.kis.powp.jobs2d.command.utils.entities;

import java.util.List;

public class JsonCommandList {
    private List<JsonCommand> commands;

    public List<JsonCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<JsonCommand> commands) {
        this.commands = commands;
    }

    @Override
    public String toString() {
        return "JsonCommandList{" +
                "commands=" + commands +
                '}';
    }
}
