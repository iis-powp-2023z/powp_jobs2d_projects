package edu.kis.powp.jobs2d.command.utils;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.utils.entities.JsonCommand;
import edu.kis.powp.jobs2d.command.utils.entities.JsonCommandList;
import edu.kis.powp.jobs2d.commons.FilePath;
import junit.framework.TestCase;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;

public class JsonCommandLoaderTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testLoadFromInvalidFile() {
        String path = FilePath.getAbsoluteFilePath("src/test/resources/CommandsFiles/invalidCommandsJson.json");
        JsonCommandLoader jsonCommandLoader = new JsonCommandLoader();
        Optional<ComplexCommand> commands = jsonCommandLoader.loadFromFile(path);
        assertFalse(commands.isPresent());
    }

    public void testLoadFromValidFile() {
        String path = FilePath.getAbsoluteFilePath("src/test/resources/CommandsFiles/validCommandsJson.json");

        JsonCommandLoader jsonCommandLoader = new JsonCommandLoader();
        Optional<ComplexCommand> commands = jsonCommandLoader.loadFromFile(path);

        assertTrue(commands.isPresent());
        assertEquals(5, commands.get().getListOfCommands().size());
    }
}