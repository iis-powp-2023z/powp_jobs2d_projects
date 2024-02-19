package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.manager.CommandBoundaryCheckObserver;
import edu.kis.powp.jobs2d.command.manager.CommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;

public class CommandsFeature {

    private static CommandManager commandManager;

    public static void setupCommandManager() {
        commandManager = new CommandManager();

        LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
        commandManager.getChangePublisher().addSubscriber(loggerObserver);

        CommandBoundaryCheckObserver boundaryCheckObserver = new CommandBoundaryCheckObserver(commandManager);
        commandManager.getChangePublisher().addSubscriber(boundaryCheckObserver);
    }

    /**
     * Get manager of application driver command.
     *
     * @return plotterCommandManager.
     */
    public static CommandManager getDriverCommandManager() {
        return commandManager;
    }
}
