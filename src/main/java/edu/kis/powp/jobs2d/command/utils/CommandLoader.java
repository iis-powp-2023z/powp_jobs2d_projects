package edu.kis.powp.jobs2d.command.utils;

import edu.kis.powp.jobs2d.command.ComplexCommand;

import java.io.Reader;
import java.util.Optional;

public interface CommandLoader {
    Optional<ComplexCommand> loadFromReader(Reader reader);
}
