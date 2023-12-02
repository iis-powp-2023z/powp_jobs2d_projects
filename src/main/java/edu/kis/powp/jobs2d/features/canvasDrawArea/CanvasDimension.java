package edu.kis.powp.jobs2d.features.canvasDrawArea;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CanvasDimension {

    public enum CanvasSizes {
        A4, B5
    }

    public enum CoordinateNames {
        UPPER_LEFT, UPPER_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    private static final double SCALE = 1.5;

    private static Dimension currentDimensions;

    private static final Map<CanvasSizes, Dimension> dimensionsMap = new HashMap<>();

    private static final Map<CoordinateNames, Point> coordinatesMap = new HashMap<>();

    static {
        dimensionsMap.put(CanvasSizes.A4, new Dimension(210, 297));
        dimensionsMap.put(CanvasSizes.B5, new Dimension(176, 250));
    }

    public static void setCoordinatesMap() {
        Dimension d = getScaledCurrentDimensions();
        coordinatesMap.put(CoordinateNames.UPPER_LEFT, new Point(-d.width / 2, -d.height / 2));
        coordinatesMap.put(CoordinateNames.UPPER_RIGHT, new Point(d.width / 2, -d.height / 2));
        coordinatesMap.put(CoordinateNames.BOTTOM_LEFT, new Point(-d.width / 2, d.height / 2));
        coordinatesMap.put(CoordinateNames.BOTTOM_RIGHT, new Point(d.width / 2, d.height / 2));
    }

    public static Dimension getDimensions(CanvasSizes size) {
        return dimensionsMap.get(size);
    }

    public static Point getCoordinatePoint(CoordinateNames coordinateName) {
        return coordinatesMap.get(coordinateName);
    }

    public static void createDimensions(Dimension newDimensions) {
        currentDimensions = newDimensions;
        setCoordinatesMap();
    }

    public static Dimension getScaledCurrentDimensions() {
        return new Dimension((int) (currentDimensions.width * SCALE), (int) (currentDimensions.height * SCALE));
    }

    public static Dimension getCurrentDimensions() {
        return currentDimensions;
    }
}
