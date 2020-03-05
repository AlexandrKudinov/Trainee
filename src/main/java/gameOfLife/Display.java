package gameOfLife;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static gameOfLife.Logic.processorsNumber;

public class Display {

    public void showMap(Graphics g, List<Boolean[][]> maps) {
        ExecutorService service = Executors.newFixedThreadPool(processorsNumber);
        for (int i = 0; i < maps.size(); i++) {
            service.submit(new DisplayStageThread(maps, i, g));
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
