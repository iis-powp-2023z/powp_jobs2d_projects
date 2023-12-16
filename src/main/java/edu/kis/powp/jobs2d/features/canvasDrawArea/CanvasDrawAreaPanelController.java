package edu.kis.powp.jobs2d.features.canvasDrawArea;

import javax.swing.*;
import java.awt.*;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.SetCustomCanvasDrawAreaWindow;
import edu.kis.powp.jobs2d.events.SelectCanvasA4SizeOptionListener;
import edu.kis.powp.jobs2d.events.SelectCanvasB5SizeOptionListener;
import edu.kis.powp.jobs2d.events.SelectCanvasCustomSizeOptionListener;
import edu.kis.powp.jobs2d.features.DrawerFeature;

public class CanvasDrawAreaPanelController {
    private static final Integer DRAW_AREA_BORDER = 50;
    private final JPanel backgroundArea;
    private JPanel canvasDrawArea;

    public CanvasDrawAreaPanelController(Application application) {
        this.setActionListenersAndComponentMenu(application);
        backgroundArea = application.getFreePanel();
        this.initializeCanvasDrawArea();
        this.setDefaultBackgroundColor();
        this.setPredefinedSize(CanvasDimension.CanvasSizes.A4);
    }

    public void setPredefinedSize(CanvasDimension.CanvasSizes size) {
        CanvasDimension.createDimensions(CanvasDimension.getDimensions(size));
        this.setDimensions(CanvasDimension.getScaledCurrentDimensions());
    }

    public void setCustomSize(Dimension newDimensions) {
        CanvasDimension.createDimensions(newDimensions);
        this.setDimensions(CanvasDimension.getScaledCurrentDimensions());
    }

    public void openCustomSizeWindow() {
        SetCustomCanvasDrawAreaWindow setCustomCanvasDrawAreaWindow = new SetCustomCanvasDrawAreaWindow();
    }

    public Dimension getCavasDimension() {
        return CanvasDimension.getCurrentDimensions();
    }

    private void setActionListenersAndComponentMenu(Application application) {
        SelectCanvasA4SizeOptionListener selectCanvasA4SizeOptionListener = new SelectCanvasA4SizeOptionListener();
        SelectCanvasB5SizeOptionListener selectCanvasB5SizeOptionListener = new SelectCanvasB5SizeOptionListener();
        SelectCanvasCustomSizeOptionListener selectCanvasCustomSizeOptionListener = new SelectCanvasCustomSizeOptionListener();

        application.addComponentMenu(CanvasDrawAreaPanelController.class, "Format", 0);
        application.addComponentMenuElement(CanvasDrawAreaPanelController.class, "A4",
                selectCanvasA4SizeOptionListener);
        application.addComponentMenuElement(CanvasDrawAreaPanelController.class, "B5",
                selectCanvasB5SizeOptionListener);
        application.addComponentMenuElement(CanvasDrawAreaPanelController.class, "Custom size",
                selectCanvasCustomSizeOptionListener);
    }

    private void initializeCanvasDrawArea() {
        canvasDrawArea = new JPanel();
        backgroundArea.add(canvasDrawArea, BorderLayout.CENTER);
        backgroundArea.setLayout(new GridBagLayout());
        DrawerFeature.getDrawerController().initialize(canvasDrawArea);
    }

    private void setDefaultBackgroundColor() {
        this.canvasDrawArea.setBackground(Color.white);
        this.backgroundArea.setBackground(Color.lightGray);
    }

    private void setDimensions(Dimension newDimensions) {
        backgroundArea.setPreferredSize(
                new Dimension(newDimensions.width + DRAW_AREA_BORDER, newDimensions.height + DRAW_AREA_BORDER));
        backgroundArea.setSize(newDimensions);

        canvasDrawArea.setPreferredSize(newDimensions);
        canvasDrawArea.setSize(newDimensions);
    }
}
