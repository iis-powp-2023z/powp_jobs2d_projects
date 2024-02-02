package edu.kis.powp.jobs2d.command.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.*;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.observer.Subscriber;

public class CommandManagerWindow extends JFrame implements WindowComponent {

    private CommandManager commandManager;

    private JTextArea currentCommandField;

    private String observerListString;
    private String commadnString;
    private JTextArea observerListField;
    private JTextArea commandListField;
    private JPanel drawingPanel;

    /**
     *
     */
    private static final long serialVersionUID = 9204679248304669948L;

    public CommandManagerWindow(CommandManager commandManager) {
        this.setTitle("Command Manager");
        this.setSize(1000, 600);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;

        GridBagConstraints c = new GridBagConstraints();

        observerListField = new JTextArea("");
        observerListField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(observerListField, c);
        updateObserverListField();

        currentCommandField = new JTextArea("");
        currentCommandField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 1;
        c.weighty = 1;
        content.add(currentCommandField, c);
        updateCurrentCommandField();

        JButton btnClearCommand = new JButton("Clear command");
        btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearCommand, c);

        JButton btnClearObservers = new JButton("Delete observers");
        btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearObservers, c);

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                drawCommands(g);
            }
        };

        drawingPanel.setPreferredSize(new Dimension(300, 300));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 1;
        c.weighty = 1;
        content.add(drawingPanel, c);

        commandListField = new JTextArea("");
        commandListField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 3;
        c.gridheight = 3;
        c.weighty = 1;
        content.add(commandListField, c);
        printCommands();
    }

    private void clearCommand() {
        commandManager.clearCurrentCommand();
        updateCurrentCommandField();
        printCommands();
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
    }

    public void deleteObservers() {
        commandManager.getChangePublisher().clearObservers();
        this.updateObserverListField();
    }

    private void updateObserverListField() {
        observerListString = "";
        List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
        for (Subscriber observer : commandChangeSubscribers) {
            observerListString += observer.toString() + System.lineSeparator();
        }
        if (commandChangeSubscribers.isEmpty())
            observerListString = "No observers loaded";

        observerListField.setText(observerListString);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        updateObserverListField();
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }
    public void drawCommands(Graphics g) {
        int startPosX = 0, startPosY = 0, endPosX = 0, endPosY = 0;
        g.translate(150, 150);
        DriverCommand currentCommand = commandManager.getCurrentCommand();
        if (currentCommand instanceof ComplexCommand) {
            List<DriverCommand> commandsList = ((ComplexCommand) currentCommand).getListOfCommands();
            for (DriverCommand cmd : commandsList) {
                if (cmd instanceof SetPositionCommand) {
                    startPosX = ((SetPositionCommand) cmd).getPosX();
                    startPosY = ((SetPositionCommand) cmd).getPosY();
                }
                if (cmd instanceof OperateToCommand) {
                    endPosX = ((OperateToCommand) cmd).getPosX();
                    endPosY = ((OperateToCommand) cmd).getPosY();
                    g.drawLine(startPosX, startPosY, endPosX, endPosY);
                    startPosX = endPosX;
                    startPosY = endPosY;
                }
            }
        }
    }
    public void printCommands(){
        commadnString = "Command list:" + System.lineSeparator();
        DriverCommand currentCommand = commandManager.getCurrentCommand();
        if (currentCommand instanceof ComplexCommand) {
            List<DriverCommand> commandsList = ((ComplexCommand) currentCommand).getListOfCommands();

            for (DriverCommand cmd : commandsList) {
                commadnString += System.lineSeparator() + cmd;
            }
        }
        commandListField.setText(commadnString);
    }
}
