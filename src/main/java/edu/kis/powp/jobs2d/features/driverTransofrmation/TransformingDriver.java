package edu.kis.powp.jobs2d.features.driverTransofrmation;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Decorator for Job2dDriver that applies transformations before forwarding the instructions.
 */
public class TransformingDriver implements Job2dDriver {
    private final Job2dDriver driver;
    private final List<TransformationModifier> modifiers;

    public TransformingDriver(Job2dDriver driver) {
        this.driver = driver;
        this.modifiers = new ArrayList<>();
    }

    public void addModifier(TransformationModifier modifier) {
        modifiers.add(modifier);
    }

    public void removeModifier(TransformationModifier modifier)
    {
        modifiers.remove(modifier);
    }

    @Override
    public void setPosition(int x, int y) {
        int[] transformed = transformCoordinates(x, y);
        driver.setPosition(transformed[0], transformed[1]);
    }

    @Override
    public void operateTo(int x, int y) {
        int[] transformed = transformCoordinates(x, y);
        driver.operateTo(transformed[0], transformed[1]);
    }

    private int[] transformCoordinates(int x, int y) {
        for (TransformationModifier modifier : modifiers) {
            int[] result = modifier.modify(x, y);
            x = result[0];
            y = result[1];
        }
        return new int[]{x, y};
    }

    @Override
    public String toString() {
        return driver.toString();
    }
}

