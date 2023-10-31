package edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers;

import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformationModifier;

public class ScalingModifier implements TransformationModifier {
    private final double scaleX;
    private final double scaleY;

    public ScalingModifier(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public int[] modify(int x, int y) {
        return new int[]{(int) (x * this.scaleX), (int) (y * this.scaleY)};
    }
}
