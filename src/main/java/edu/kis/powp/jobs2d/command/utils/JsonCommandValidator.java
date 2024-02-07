
package edu.kis.powp.jobs2d.command.utils;

import edu.kis.powp.jobs2d.command.utils.entities.JsonCommand;

import java.util.List;
import java.util.logging.Logger;

public class JsonCommandValidator {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static boolean isJsonCommandsValid(List<JsonCommand> commands)
    {
        for (JsonCommand command : commands) {
            if (!isJsonCommandValid(command)) return false;
        }

        return true;
    }

    public static boolean isJsonCommandValid(JsonCommand command)
    {
        if (!(command.getType().equals(JsonCommand.OPERATE_TO_COMMAND_TYPE) || command.getType().equals(JsonCommand.SET_POSITION_COMMAND_TYPE))) {
            logger.warning("Invalid command type: " + command.getType());
            return false;
        }

        if (command.getX() < 0 || command.getY() < 0) {
            logger.warning("Invalid position: " + command.getX() + ", " + command.getY());
            return false;
        }

        return true;
    }
}
