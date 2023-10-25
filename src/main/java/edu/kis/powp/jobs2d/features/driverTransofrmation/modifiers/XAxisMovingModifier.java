package edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers;

import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformationModifier;

public class XAxisMovingModifier implements TransformationModifier {
    private final int shiftX;

    public XAxisMovingModifier(int shiftX) {
        this.shiftX = shiftX;
    }

    @Override
    public int[] modify(int x, int y) {
        return new int[]{(x + this.shiftX), y};
    }
}
