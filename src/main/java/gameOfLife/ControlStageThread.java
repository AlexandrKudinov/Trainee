package gameOfLife;

import java.util.concurrent.Callable;

import static gameOfLife.Display.BLOCK;
import static gameOfLife.GameField.HEIGHT;
import static gameOfLife.GameField.WIDTH;

public class ControlStageThread implements Callable<Boolean[][]> {
    private boolean[][] map;

    public ControlStageThread(boolean[][] map) {
        this.map = map;
    }

    @Override
    public Boolean[][] call() {
        Boolean[][] newMap = new Boolean[WIDTH / BLOCK][HEIGHT / BLOCK];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int surroundLife = getSurroundLife(i, j);
                if (surroundLife == 3) {
                    newMap[i][j] = true;
                } else newMap[i][j] = surroundLife == 2 && map[i][j];
            }
        }
        return newMap;
    }

    private int getSurroundLife(int i, int j) {
        Location.setMap(map);
        for (Location location : Location.values()) {
            location.control(i, j);
        }
        return Location.getSurroundLife();
    }
}
