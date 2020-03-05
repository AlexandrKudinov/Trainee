package gameOfLife;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

import static gameOfLife.GameField.*;
import static java.util.stream.IntStream.*;

public class Logic {
    public static int processorsNumber = Runtime.getRuntime().availableProcessors();
    private int partitionSize = (HEIGHT / BLOCK) / processorsNumber;
    private List<Boolean[][]> mapParts = new LinkedList<>();

    public Logic() {
        for (int i = 0; i < processorsNumber; i++) {
            Boolean[][] booleans = range(0, Math.min(partitionSize, HEIGHT / BLOCK - i * partitionSize))
                    .mapToObj(x -> range(0, WIDTH / BLOCK)
                            .mapToObj(y -> false)
                            .toArray(Boolean[]::new))
                    .toArray(Boolean[][]::new);
            mapParts.add(booleans);
        }
    }

    public List<Boolean[][]> getMapParts() {
        return mapParts;
    }

    public void setElementStage(boolean changeStage, int xPosition, int yPosition) {
        Boolean[][] map = mapParts.get(yPosition / (partitionSize * BLOCK));
        int i = (yPosition % (partitionSize * BLOCK)) / BLOCK;
        int j = xPosition / BLOCK;
        map[i][j] = !changeStage || !map[i][j];
    }

    public void updateMap() {
        ExecutorService service = Executors.newFixedThreadPool(processorsNumber);
        List<Boolean[][]> newMapParts = new LinkedList<>();
        for (int i = 0; i < mapParts.size(); i++) {
            Future future = service.submit(new ControlStageThread(mapParts, i));
            try {
                newMapParts.add((Boolean[][]) future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mapParts = newMapParts;
    }

    public void clearMap() {
        for (int i = 0; i < processorsNumber; i++) {
            mapParts.set(i, Arrays.stream(mapParts.get(i))
                    .map(y -> Arrays.stream(y)
                            .map(x -> false)
                            .toArray(Boolean[]::new))
                    .toArray(Boolean[][]::new));
        }
    }

}
