package edu.kis.powp.jobs2d.features.driverTransofrmation.modifiers;

import edu.kis.powp.jobs2d.features.driverTransofrmation.TransformationModifier;

public class VerticalFlipModifier implements TransformationModifier {
    public VerticalFlipModifier() {
    }

    @Override
    public int[] modify(int x, int y) {
        return new int[]{ x, (y * -1)};
    }
}
