package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.drivers.DriverMacro;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class SelectMacro2OptionListener implements ActionListener {

    private DriverMacro macroDriver;
    private CommandManager manager;
    @Override
    public void actionPerformed(ActionEvent e) {
        List<DriverCommand> commands = MacroFeature.getDriverMacro().getMacro();


        CommandManager manager = CommandsFeature.getDriverCommandManager();
        manager.setCurrentCommand(commands, "Macro");
    }
}
