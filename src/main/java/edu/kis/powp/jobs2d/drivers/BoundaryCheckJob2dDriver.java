package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.visitor.CanvasBoundaryCheckVisitor;
import edu.kis.powp.jobs2d.features.DrawerFeature;

import java.awt.*;

public class BoundaryCheckJob2dDriver implements Job2dDriver {
    private Job2dDriver driver;
    public Dimension canvasDimension = DrawerFeature.getCanvasDrawAreaPanelController().getCanvasDimension();
    public CanvasBoundaryCheckVisitor boundaryCheckVisitor = new CanvasBoundaryCheckVisitor(canvasDimension);

    public BoundaryCheckJob2dDriver(Job2dDriver driver) {
        this.driver = driver;
    }

    @Override
    public void operateTo(int x, int y) {
        boundaryCheckVisitor.visitOperateToCommand(new OperateToCommand(x, y));

        if (!boundaryCheckVisitor.exceedsBounds()) {
            driver.operateTo(x, y);
        }
    }

    @Override
    public void setPosition(int x, int y) {
        boundaryCheckVisitor.visitSetPositionCommand(new SetPositionCommand(x, y));
        driver.setPosition(x, y);

        if (!boundaryCheckVisitor.exceedsBounds()) {
            driver.setPosition(x, y);
        }
    }

    public boolean doesExceedCanvas() {
        return boundaryCheckVisitor.exceedsBounds();
    }
}
