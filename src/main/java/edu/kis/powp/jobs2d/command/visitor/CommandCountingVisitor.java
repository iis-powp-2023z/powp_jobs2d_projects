package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class CommandCountingVisitor implements CommandVisitor {

    private int complexCommandCount = 0;
    private int operateToCommandCount = 0;
    private int setPositionCommandCount = 0;

    @Override
    public void visitComplexCommand(ICompoundCommand complexCommand) {
        complexCommandCount++;
    }

    @Override
    public void visitOperateToCommand(OperateToCommand operateToCommand) {
        operateToCommandCount++;
    }

    @Override
    public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
        setPositionCommandCount++;
    }

    @Override
    public String toString() {
        return "CommandCountingVisitor{" +
                "complexCommandCount=" + complexCommandCount +
                ", operateToCommandCount=" + operateToCommandCount +
                ", setPositionCommandCount=" + setPositionCommandCount +
                '}';
    }
}
