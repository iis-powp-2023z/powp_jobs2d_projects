package edu.kis.powp.jobs2d.features.canvasDrawArea;

import edu.kis.powp.jobs2d.features.canvasDrawArea.sizes.GenericCanvasSize;

import java.util.HashMap;
import java.util.Map;

public class CanvasSizeFactory {
    private static final Map<String, GenericCanvasSize> sizes = new HashMap<>();

    static {
        sizes.put("A4", new GenericCanvasSize(210, 297));
        sizes.put("B5", new GenericCanvasSize(176, 250));
    }

    public static CanvasSize getCanvasSize(String label) {
        return sizes.get(label);
    }

    public static HashMap<String, GenericCanvasSize> getSizes() {
        return (HashMap<String, GenericCanvasSize>) sizes;
    }

    public static GenericCanvasSize getDefaultCanvasSize() {
        return sizes.get("A4");
    }
}
