package edu.kis.powp.jobs2d.command.utils;

import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

public class JsonCommandReader {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static JsonReader read(String path) {
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            logger.warning("Invalid file path");
            return null;
        }
        return reader;
    }
}
