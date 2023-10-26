package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.ComplexCommandFactory;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTestClone2OptionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommand comandToClone = ComplexCommandFactory.getRectangle();
        CommandManager manager = CommandsFeature.getDriverCommandManager();
        manager.setCurrentCommand(comandToClone.clone());
    }
}
