package edu.kis.powp.jobs2d.command.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.utils.entities.JsonCommand;
import edu.kis.powp.jobs2d.command.utils.entities.JsonCommandList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;
import java.util.logging.Logger;

public class JsonCommandLoader implements CommandLoader {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public Optional<ComplexCommand> loadFromFile(String path) {
        Gson gson = new Gson();
        JsonReader reader;

        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            logger.warning("Invalid file path");
            return Optional.empty();
        }

        JsonCommandList commandList;

        try {
            commandList = gson.fromJson(reader, JsonCommandList.class);
        } catch (JsonSyntaxException e) {
            logger.warning("Invalid json format");
            return Optional.empty();
        }

        // validate data
        for (JsonCommand command : commandList.getCommands()) {
            if (!(command.getType().equals(JsonCommand.OPERATE_TO_COMMAND_TYPE) || command.getType().equals(JsonCommand.SET_POSITION_COMMAND_TYPE))) {
                logger.warning("Invalid command type: " + command.getType());
                return Optional.empty();
            }
            if (command.getX() < 0 || command.getY() < 0) {
                logger.warning("Invalid position: " + command.getX() + ", " + command.getY());
                return Optional.empty();
            }
        }

        logger.info("Successfully loaded commands: " + commandList.toString());

        ComplexCommand driverCommand = commandList.toComplexCommand();
        return Optional.of(driverCommand);
    }
}
