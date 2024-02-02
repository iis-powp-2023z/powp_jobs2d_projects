package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.awt.*;

public class DrawCommandVisitor implements CommandVisitor {

    private Graphics graphics;
    private int startPosX = 0,startPosY = 0;
    public DrawCommandVisitor(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void visitComplexCommand(ComplexCommand complexCommand) {
        for (DriverCommand cmd : complexCommand.getListOfCommands()) {
            cmd.accept(this);
        }
    }

    @Override
    public void visitOperateToCommand(OperateToCommand operateToCommand) {
        int endPosX = operateToCommand.getPosX();
        int endPosY = operateToCommand.getPosY();
        graphics.drawLine(startPosX, startPosY, endPosX, endPosY);
        startPosX = endPosX;
        startPosY = endPosY;
    }

    @Override
    public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
        startPosX = setPositionCommand.getPosX();
        startPosY = setPositionCommand.getPosY();
    }
}