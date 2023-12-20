package edu.kis.powp.jobs2d.command.utils;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.utils.entities.JsonCommandList;

import java.util.Optional;

public interface CommandLoader {
    Optional<ComplexCommand> loadFromFile(String path);
}
