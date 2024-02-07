package edu.kis.powp.jobs2d.command.utils;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.utils.entities.JsonCommandList;

import java.io.Reader;
import java.util.Optional;
import java.util.logging.Logger;

public class JsonCommandLoader implements CommandLoader {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public Optional<ComplexCommand> loadFromReader(Reader reader) {
        // parse json to commands
        JsonCommandList commandList = JsonCommandParser.parse(reader);
        if (commandList == null) {
            return Optional.empty();
        }

        // validate parsed commands
        boolean ok = JsonCommandValidator.isJsonCommandsValid(commandList.getCommands());
        if (!ok) {
            return Optional.empty();
        }

        logger.info("Successfully loaded commands: " + commandList);

        ComplexCommand driverCommand = commandList.toComplexCommand();
        return Optional.of(driverCommand);
    }
}