package gameOfLife;


import java.awt.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

import static gameOfLife.Display.BLOCK;
import static gameOfLife.GameField.*;

public class Logic {
    private boolean[][] map = new boolean[WIDTH / BLOCK][HEIGHT / BLOCK];
    private int processorsNumber = Runtime.getRuntime().availableProcessors();
    private int partitionSize = map.length / processorsNumber;
    List<Boolean[][]> mapParts = new LinkedList<>();

    public void setMap(boolean[][] map) {
        this.map = map;
    }

//    private List<Boolean[][]> getMapParts(){
//
//
//
//    }


    public boolean[][] getMap() {
        return map;
    }

    public void changeMapStageByClick(int xPosition, int yPosition) {
        int i = xPosition / BLOCK;
        int j = yPosition / BLOCK;
        map[i][j] = !map[i][j];
    }

    public void changeMapStageByDragging(int xPosition, int yPosition) {
        int i = xPosition / BLOCK;
        int j = yPosition / BLOCK;
        map[i][j] = true;
    }

    public void updateMap() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ExecutorService service = Executors.newFixedThreadPool(processorsNumber);

//
//        for (int i = 0; i < processorsNumber; i++) {
//            Future<Boolean[][]> future = service.submit(new ControlStageThread(map));
//            try {
//                // = result.multiply(future.get());
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        service.shutdown();
//        try {
//            service.awaitTermination(1, TimeUnit.MINUTES);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        boolean[][] newMap = new boolean[WIDTH / BLOCK][HEIGHT / BLOCK];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int surroundLife = getSurroundLife(i, j);
                if (surroundLife == 3) {
                    newMap[i][j] = true;
                } else newMap[i][j] = surroundLife == 2 && map[i][j];
            }
        }
        map = newMap;
    }

    public int getSurroundLife(int i, int j) {
        Location.setMap(map);
        for (Location location : Location.values()) {
            location.control(i, j);
        }
        return Location.getSurroundLife();
    }


}
