package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import java.awt.Dimension;
import java.awt.Point;

public class CanvasBoundaryCheckVisitor implements CommandVisitor {
    private int minX = Integer.MAX_VALUE;
    private int maxX = Integer.MIN_VALUE;
    private int minY = Integer.MAX_VALUE;
    private int maxY = Integer.MIN_VALUE;

    private final Dimension canvasDimension;

    public CanvasBoundaryCheckVisitor(Dimension canvasDimension) {
        this.canvasDimension = canvasDimension;
    }

    @Override
    public void visitOperateToCommand(OperateToCommand operateToCommand) {
        updateDrawingBoundaryLimits(operateToCommand.getPosX(), operateToCommand.getPosY());
    }

    @Override
    public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
        updateDrawingBoundaryLimits(setPositionCommand.getPosX(), setPositionCommand.getPosY());
    }

    @Override
    public void visitComplexCommand(ComplexCommand complexCommand) {
        complexCommand.iterator().forEachRemaining(command -> command.accept(this));
    }

    private void updateDrawingBoundaryLimits(int x, int y) {
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
    }

    public boolean exceedsBounds() {
        Point topLeft = new Point(-canvasDimension.width / 2, -canvasDimension.height / 2);
        Point bottomRight = new Point(canvasDimension.width / 2, canvasDimension.height / 2);
        if(minX < topLeft.x || maxX > bottomRight.x || minY < topLeft.y || maxY > bottomRight.y) {
            return true;
        }
        return false;
    }

}
