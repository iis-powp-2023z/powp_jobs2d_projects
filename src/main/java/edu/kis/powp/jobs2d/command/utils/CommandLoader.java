package edu.kis.powp.jobs2d.command.utils;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.utils.entities.JsonCommandList;

import java.util.Optional;

public interface CommandLoader {
    Optional<JsonCommandList> loadFromFile(String path);
    boolean exportToFile(DriverCommand command, String path);
}
