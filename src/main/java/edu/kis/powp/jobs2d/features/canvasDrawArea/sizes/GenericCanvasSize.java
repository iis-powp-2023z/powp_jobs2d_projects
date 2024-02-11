package edu.kis.powp.jobs2d.features.canvasDrawArea.sizes;

import edu.kis.powp.jobs2d.features.canvasDrawArea.CanvasSize;

import java.awt.*;

public class GenericCanvasSize implements CanvasSize {
    private final Dimension dimension;

    public GenericCanvasSize(int width, int height) {
        this.dimension = new Dimension(width, height);
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

}
