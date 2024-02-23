package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.visitor.CanvasBoundaryCheckVisitor;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.observer.Subscriber;

import java.awt.Dimension;
import java.util.logging.Logger;

public class CommandBoundaryCheckObserver implements Subscriber {
    private final CommandManager commandManager;

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public CommandBoundaryCheckObserver(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.commandManager.getChangePublisher().addSubscriber(this);
    }

    @Override
    public void update() {
        Dimension canvasDimension = DrawerFeature.getCanvasDrawAreaPanelController().getCanvasDimension();
        CanvasBoundaryCheckVisitor boundaryCheckVisitor = new CanvasBoundaryCheckVisitor(canvasDimension);

        DriverCommand currentCommand = commandManager.getCurrentCommand();
        boundaryCheckVisitor.resetBoundaryCheck();
        currentCommand.accept(boundaryCheckVisitor);

        if (boundaryCheckVisitor.getBoundaryExceed()) {
            logger.warning("Operation exceeds boundaries of canvas");
        }
    }
}
