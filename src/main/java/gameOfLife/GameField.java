package gameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameField extends JPanel {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int BLOCK = 4;
    private Display display;
    private Logic logic;

    public GameField(Display display, Logic logic) {
        this.display = display;
        this.logic = logic;
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                logic.setElementStage(true, e.getX(), e.getY());
                GameField.super.repaint();
            }

        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                logic.setElementStage(false, e.getX(), e.getY());
                GameField.super.repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            Timer timer = new Timer(10, evt -> update());

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE: {
                        if (timer.isRunning()) {
                            timer.stop();
                        } else timer.start();
                        break;
                    }
                    case KeyEvent.VK_ESCAPE: {
                        timer.stop();
                        logic.clearMap();
                        GameField.super.repaint();
                        break;
                    }
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        display.showMap(g, logic.getMapParts());
    }

    private void update() {
        logic.updateMap();
        GameField.super.repaint();
    }

}
