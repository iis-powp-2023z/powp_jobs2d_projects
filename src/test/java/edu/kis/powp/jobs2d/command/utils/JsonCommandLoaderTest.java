package edu.kis.powp.jobs2d.command.utils;

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
        Optional<JsonCommandList> commands = jsonCommandLoader.loadFromFile(path);
        assertFalse(commands.isPresent());
    }

    public void testLoadFromValidFile() {
        JsonCommandList expectedList = new JsonCommandList();

        List<JsonCommand> expectedCommands = new ArrayList<>();
        expectedCommands.add(new JsonCommand("operateTo", 10, 10));
        expectedCommands.add(new JsonCommand("setPosition", 20, 20));
        expectedCommands.add(new JsonCommand("operateTo", 19, 33));
        expectedCommands.add(new JsonCommand("operateTo", 8, 1));
        expectedCommands.add(new JsonCommand("setPosition", 0, 0));

        expectedList.setCommands(expectedCommands);

        String path = FilePath.getAbsoluteFilePath("src/test/resources/CommandsFiles/validCommandsJson.json");

        JsonCommandLoader jsonCommandLoader = new JsonCommandLoader();
        Optional<JsonCommandList> commands = jsonCommandLoader.loadFromFile(path);

        for(int i = 0; i < commands.get().getCommands().size(); i++) {
            assertEquals(expectedList.getCommands().get(i).getX(), commands.get().getCommands().get(i).getX());
            assertEquals(expectedList.getCommands().get(i).getY(), commands.get().getCommands().get(i).getY());
        }
    }


    public void testExportToFile() {
    }
}