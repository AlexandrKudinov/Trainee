package gameOfLife;

import java.awt.*;


public class Display {
    public static final int BLOCK = 3;

    private void showLife(Graphics g, int x, int y) {
        g.setColor(Color.black);
        g.fillRect(x * BLOCK, y * BLOCK, BLOCK, BLOCK);
    }

    private void showDepth(Graphics g, int x, int y) {
        g.setColor(Color.white);
        g.fillRect(x * BLOCK, y * BLOCK, BLOCK, BLOCK);
    }

    public void showMap(Graphics g, boolean[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j]) {
                    showLife(g, i, j);
                } else {
                    showDepth(g, i, j);
                }
            }
        }
    }


}
