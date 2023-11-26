package edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers;

import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformationModifier;

public class ShiftAxesModifier implements TransformationModifier {
    private final int shiftX;
    private final int shiftY;

    public ShiftAxesModifier(int shiftX, int shiftY) {
        this.shiftX = shiftX;
        this.shiftY = shiftY;
    }

    @Override
    public int[] modify(int x, int y) {
        return new int[]{(x + this.shiftX), (y + this.shiftY)};
    }
}
