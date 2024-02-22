package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import java.awt.Dimension;
import java.awt.Point;

public class CanvasBoundaryCheckVisitor implements CommandVisitor {
    private final Dimension canvasDimension;

    private boolean boundaryExceeded = false;

    public CanvasBoundaryCheckVisitor(Dimension canvasDimension) {
        this.canvasDimension = canvasDimension;
    }

    public void resetBoundaryCheck() {
        boundaryExceeded = false;
    }

    public boolean getBoundaryExceed() {
        return boundaryExceeded;
    }

    @Override
    public void visitOperateToCommand(OperateToCommand operateToCommand) {
        if(exceedsBounds(operateToCommand.getPosX(), operateToCommand.getPosY())) {
            boundaryExceeded = true;
        }
    }

    @Override
    public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
        if(exceedsBounds(setPositionCommand.getPosX(), setPositionCommand.getPosY())) {
            boundaryExceeded = true;
        }
    }

    @Override
    public void visitComplexCommand(ComplexCommand complexCommand) {
        complexCommand.iterator().forEachRemaining(command -> command.accept(this));
    }

    public boolean exceedsBounds(int x, int y) {
        Point topLeft = new Point(-canvasDimension.width / 2, -canvasDimension.height / 2);
        Point bottomRight = new Point(canvasDimension.width / 2, canvasDimension.height / 2);
        return x < topLeft.x || x > bottomRight.x || y < topLeft.y || y > bottomRight.y;
    }
}
