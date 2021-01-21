package org.iesanaluisabenitez.informatica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppForm {
    private JButton resetButton;
    private JPanel paintPanel;
    private JPanel mainPanel;
    private JButton startButton;

    public Painter painter;

    private boolean stop = true;

    public AppForm() {
        paintPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                JPanel panel = (JPanel) e.getSource();

                painter.click((Graphics2D) panel.getGraphics(), panel.getWidth(), panel.getHeight(),
                        e.getButton(), e.getX(), e.getY());
            }
        });


        startButton.addActionListener(e -> {
            this.stop = !this.stop;
            if (!this.stop) {
                this.startButton.setText("stop");
                this.run();
            } else {
                this.startButton.setText("start");
            }
        });

        this.painter = new Painter();
        ((PaintPanel) this.paintPanel).painter = this.painter;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }


    private static AppForm appForm;

    public static void main(String[] args) throws InterruptedException {
        appForm = new AppForm();

        JFrame frame = new JFrame("AppForm");
        frame.setContentPane(appForm.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        appForm.run();


    }

    private static final int UPDATE_DIFFERENCE = 1000 / 60;
    private  void run() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            //create a new thread to keep the GUI responsive while the game runs
            Thread t = new Thread(() -> {
                long startTime = System.currentTimeMillis();
                while(!appForm.stop) {
                    long time = UPDATE_DIFFERENCE- (System.currentTimeMillis() - startTime) ;
                    if(time < 0) {
                        appForm.paintPanel.validate();
                        appForm.paintPanel.repaint();
                        startTime = System.currentTimeMillis();
                    } else {
                        try {
                            Thread.sleep(time);
                        } catch (InterruptedException ignored) {

                        }
                    }
                }
            });
            t.start();
        });
    }
}
