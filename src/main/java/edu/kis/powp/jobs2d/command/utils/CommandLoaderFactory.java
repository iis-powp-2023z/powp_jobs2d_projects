package edu.kis.powp.jobs2d.command.utils;

public class CommandLoaderFactory {
    public static CommandLoader getCommandLoader(CommandLoaderType type) {
        switch (type) {
            case JSON:
                return new JsonCommandLoader();
            default:
                throw new RuntimeException("Unsupported file format: " + type);
        }
    }
}
