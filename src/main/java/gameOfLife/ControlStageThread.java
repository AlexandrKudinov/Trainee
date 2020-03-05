package gameOfLife;

import java.util.List;
import java.util.concurrent.Callable;

import static gameOfLife.GameField.BLOCK;
import static gameOfLife.GameField.WIDTH;

public class ControlStageThread implements Callable<Boolean[][]> {
    private Boolean[][] currentMap;
    private int currentMapIndex;
    private List<Boolean[][]> mapParts;

    public ControlStageThread(List<Boolean[][]> mapParts, int currentMapIndex) {
        this.currentMap = mapParts.get(currentMapIndex);
        this.currentMapIndex = currentMapIndex;
        this.mapParts = mapParts;
    }

    @Override
    public Boolean[][] call() {
        Boolean[][] newMap = new Boolean[currentMap.length][WIDTH / BLOCK];
        for (int i = 0; i < currentMap.length; i++) {
            for (int j = 0; j < currentMap[i].length; j++) {
                newMap[i][j] = false;
                int surroundLife = getSurroundLife(i, j);
                if (surroundLife == 3) {
                    newMap[i][j] = true;
                } else newMap[i][j] = surroundLife == 2 && currentMap[i][j];
            }
        }
        return newMap;
    }

    private int getSurroundLife(int i, int j) {
        Location.setMap(mapParts, currentMapIndex);
        for (Location location : Location.values()) {
            location.control(i, j);
        }
        return Location.getSurroundLife();
    }
}
