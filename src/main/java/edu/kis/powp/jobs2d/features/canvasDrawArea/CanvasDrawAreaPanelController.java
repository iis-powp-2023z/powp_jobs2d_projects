package edu.kis.powp.jobs2d.features.canvasDrawArea;

import javax.swing.*;
import java.awt.*;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.SetCustomCanvasDrawAreaWindow;
import edu.kis.powp.jobs2d.events.SelectCanvasOptionListener;
import edu.kis.powp.jobs2d.features.DrawerFeature;

public class CanvasDrawAreaPanelController {
    private static final Integer DRAW_AREA_BORDER = 50;
    private final JPanel backgroundArea;
    private JPanel canvasDrawArea;
    private final CanvasSizeProvider sizeProvider;

    public CanvasDrawAreaPanelController(Application application, CanvasSizeProvider sizeProvider) {
        this.sizeProvider = sizeProvider;
        this.setActionListenersAndComponentMenu(application);
        backgroundArea = application.getFreePanel();
        this.initializeCanvasDrawArea();
        this.setDefaultBackgroundColor();
        this.setPredefinedSize(CanvasSizeFactory.getCanvasSize("A4"));
    }

     public void setPredefinedSize(CanvasSize canvasSize) {
            sizeProvider.setCurrentDimension(canvasSize.getDimension());
            this.setDimensions(sizeProvider.getCurrentDimension());
    }

    public void setCustomSize(Dimension newDimensions) {
        sizeProvider.setCurrentDimension(newDimensions);
        this.setDimensions(sizeProvider.getCurrentDimension());
    }

    public void openCustomSizeWindow() {
        SetCustomCanvasDrawAreaWindow setCustomCanvasDrawAreaWindow = new SetCustomCanvasDrawAreaWindow();
    }

    public Dimension getCanvasDimension() {
        return sizeProvider.getCurrentDimension();
    }

    private void setActionListenersAndComponentMenu(Application application) {
        application.addComponentMenu(CanvasDrawAreaPanelController.class, "Format", 0);
        CanvasSizeFactory.getSizes().forEach((label, size) -> application.addComponentMenuElement(CanvasDrawAreaPanelController.class, label, e -> setPredefinedSize(size)));
        application.addComponentMenuElement(CanvasDrawAreaPanelController.class, "Custom size",
                new SelectCanvasOptionListener());
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
