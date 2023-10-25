package edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers;

import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformationModifier;

public class YAxisMovingModifier implements TransformationModifier {
    private final int shiftY;

    public YAxisMovingModifier(int shiftY) {
        this.shiftY = shiftY;
    }

    @Override
    public int[] modify(int x, int y) {
        return new int[]{x, (y + shiftY)};
    }
}
