package org.iesanaluisabenitez.informatica;

import javax.swing.*;
import java.awt.*;

public class PaintPanel extends JPanel {
    public Painter painter = null;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.painter != null) {
            this.painter.repaint(g2d, this.getWidth(), this.getHeight());
        }
    }
}
