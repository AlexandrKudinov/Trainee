package gameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static gameOfLife.Display.*;


public class GameField extends JPanel {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private int xPosition;
    private int yPosition;
    private Display display = new Display();
    private Logic logic = new Logic();


    public GameField() {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xPosition = e.getX();
                yPosition = e.getY();
                System.out.println(xPosition + " , " + yPosition);
                logic.changeMapStageByClick(xPosition, yPosition);
                GameField.super.repaint();
            }

        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                xPosition = e.getX();
                yPosition = e.getY();
                logic.changeMapStageByDragging(xPosition, yPosition);
                GameField.super.repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            Timer timer = new Timer(1, evt -> update());

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_SPACE) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else timer.start();
                } else

                if (key == KeyEvent.VK_ESCAPE) {
                    timer.stop();
                    logic.setMap(new boolean[WIDTH / BLOCK][HEIGHT / BLOCK]);
                    GameField.super.repaint();
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        display.showMap(g,logic.getMap());
    }

    private void update() {
        logic.updateMap();
        GameField.super.repaint();
    }

}
