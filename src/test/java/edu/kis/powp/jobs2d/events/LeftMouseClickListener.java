package edu.kis.powp.jobs2d.events;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LeftMouseClickListener implements MouseListener {

    private Application application;
    private final Job2dDriver driver;

    public LeftMouseClickListener(Application application, Job2dDriver driver) {
        this.application = application;
        this.driver = driver;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int width = application.getFreePanel().getWidth();
        int height = application.getFreePanel().getHeight();

        if (e.getButton() == MouseEvent.BUTTON3) {
            Point point = e.getPoint();
            driver.setPosition(point.x - (width / 2), point.y - (height / 2));
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            Point point = e.getPoint();
            driver.operateTo(point.x - (width / 2), point.y - (height / 2));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
