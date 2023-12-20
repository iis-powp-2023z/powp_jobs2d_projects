package edu.kis.powp.jobs2d.command.utils;

import edu.kis.powp.jobs2d.command.utils.entities.JsonCommand;
import edu.kis.powp.jobs2d.command.utils.entities.JsonCommandList;
import junit.framework.TestCase;

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
        String path = "C:\\Edu\\Sem7\\powp\\powp_jobs2d_projects\\src\\test\\CommandsFiles\\invalidCommandsJson.json";
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

        String path = "C:\\Edu\\Sem7\\powp\\powp_jobs2d_projects\\src\\test\\CommandsFiles\\validCommandsJson.json";
        JsonCommandLoader jsonCommandLoader = new JsonCommandLoader();
        Optional<JsonCommandList> commands = jsonCommandLoader.loadFromFile(path);

        assertTrue(commands.isPresent());
    }


    public void testExportToFile() {
    }
}