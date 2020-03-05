package gameOfLife;

import java.awt.*;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static gameOfLife.GameField.*;
import static gameOfLife.Logic.processorsNumber;

public class DisplayStageThread implements Runnable {
    private final static Lock lock = new ReentrantLock();
    private Boolean[][] currentMap;
    private Graphics graphics;
    private int coefficient;

    public DisplayStageThread(List<Boolean[][]> mapParts, int mapIndex, Graphics g) {
        this.currentMap = mapParts.get(mapIndex);
        this.graphics = g;
        this.coefficient = mapIndex * ((HEIGHT / BLOCK) / processorsNumber);
    }

    @Override
    public void run() {
        for (int i = 0; i < currentMap.length; i++) {
            for (int j = 0; j < currentMap[i].length; j++) {
                showLife(currentMap[i][j], graphics, j, i + coefficient);
            }
        }
    }

    private void showLife(boolean isLife, Graphics g, int x, int y) {
        lock.lock();
        Color color = isLife ? Color.black : Color.lightGray;
        g.setColor(color);
        g.fillRect(x * BLOCK, y * BLOCK, BLOCK, BLOCK);
        lock.unlock();
    }


}
