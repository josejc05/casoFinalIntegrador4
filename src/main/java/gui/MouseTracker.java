package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MouseTracker extends JLabel {
    public MouseTracker() {
        super("Mouse at (?, ?)");
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                setText("Mouse at (" + e.getX() + ", " + e.getY() + ")");
            }
        });
    }
}