package edu.kis.powp.jobs2d.command.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.utils.entities.JsonCommandList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;
import java.util.logging.Logger;

public class JsonCommandLoader implements CommandLoader {
    private final static Logger logger = Logger.getLogger("JsonCommandLoader");

    @Override
    public Optional<JsonCommandList> loadFromFile(String path) {
        Gson gson = new Gson();
        JsonReader reader;

        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            logger.warning("Invalid file path");
            return Optional.empty();
        }

        JsonCommandList commands;

        try {
            commands = gson.fromJson(reader, JsonCommandList.class);
        } catch (JsonSyntaxException e) {
            logger.warning("Invalid json format");
            return Optional.empty();
        }

        logger.info(commands.toString());

        return Optional.of(commands);
    }

    @Override
    public boolean exportToFile(DriverCommand command, String path) {
        return false;
    }
}
