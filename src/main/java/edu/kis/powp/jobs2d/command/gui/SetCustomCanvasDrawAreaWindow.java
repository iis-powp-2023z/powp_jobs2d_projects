package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.features.DrawerFeature;

import javax.swing.*;
import java.awt.*;

public class SetCustomCanvasDrawAreaWindow extends JFrame {

    private final JTextField widthTextField;
    private final JTextField heightTextField;

    public SetCustomCanvasDrawAreaWindow() {
        super("New dimensions");
        this.setTitle("Select new dimensions");

        widthTextField = new JTextField(10);
        heightTextField = new JTextField(10);

        Dimension customDimensions = DrawerFeature.getCanvasDrawAreaPanelController().getCavasDimension();
        widthTextField.setText(Integer.toString((int) customDimensions.getWidth()));
        heightTextField.setText(Integer.toString((int) customDimensions.getHeight()));

        JLabel widthLabel = new JLabel("Width:");
        JLabel heightLabel = new JLabel("Height:");

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(widthLabel);
        inputPanel.add(widthTextField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightTextField);

        JButton submitButton = new JButton("Resize");

        submitButton.addActionListener(e -> {
            String widthText = widthTextField.getText();
            String heightText = heightTextField.getText();
            DrawerFeature.getCanvasDrawAreaPanelController().setCustomSize(new Dimension(Integer.parseInt(widthText), Integer.parseInt(heightText)));
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(submitButton, BorderLayout.SOUTH);
        add(mainPanel);
        pack();
        setVisible(true);
    }
}
