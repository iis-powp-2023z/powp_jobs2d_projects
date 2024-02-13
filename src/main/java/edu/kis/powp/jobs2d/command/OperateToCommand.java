package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.CommandVisitor;

/**
 * Implementation of Job2dDriverCommand for operateTo command functionality.
 */
public class OperateToCommand implements DriverCommand {

    private int posX, posY;

    public OperateToCommand(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void execute(Job2dDriver driver) {
        driver.operateTo(posX, posY);
    }

    @Override
    public OperateToCommand clone() {
        OperateToCommand operateToCommand;
        try {
            operateToCommand = (OperateToCommand) super.clone();
        } catch (CloneNotSupportedException e) {
            operateToCommand = new OperateToCommand(this.posX, this.posY);
        }
        return operateToCommand;
    }

    @Override
    public void accept(CommandVisitor commandVisitor) {
        commandVisitor.visitOperateToCommand(this);
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }
}
