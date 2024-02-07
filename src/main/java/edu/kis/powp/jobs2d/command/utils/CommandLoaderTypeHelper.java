package edu.kis.powp.jobs2d.command.utils;

public class CommandLoaderTypeHelper {
    public static CommandLoaderType fromString(String type) {
        switch (type.toLowerCase()) {
            case "json":
                return CommandLoaderType.JSON;
            default:
                throw new RuntimeException("Invalid command loader type: " + type);
        }
    }
}
