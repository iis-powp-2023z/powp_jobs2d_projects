package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.CanvasBoundaryCheckVisitor;
import edu.kis.powp.jobs2d.features.DrawerFeature;

import java.awt.*;
import java.util.logging.Logger;

public class BoundaryCheckJob2dDriver implements Job2dDriver {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Job2dDriver driver;
    public Dimension canvasDimension = DrawerFeature.getCanvasDrawAreaPanelController().getCanvasDimension();
    public CanvasBoundaryCheckVisitor boundaryCheckVisitor = new CanvasBoundaryCheckVisitor(canvasDimension);

    public BoundaryCheckJob2dDriver(Job2dDriver driver) {
        this.driver = driver;
    }

    @Override
    public void operateTo(int x, int y) {
        if (boundaryCheckVisitor.exceedsBounds()) {
            logger.warning("Operation exceeds boundaries of canvas");
        } else {
            driver.operateTo(x, y);
        }
    }

    @Override
    public void setPosition(int x, int y) {
        if (boundaryCheckVisitor.exceedsBounds()) {
            logger.warning("Operation exceeds boundaries of canvas");
        } else {
            driver.setPosition(x, y);
        }
    }
}
